package com.banking.util;

import com.banking.exception.DataSourceAccessException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class BasicReadWebClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicReadWebClient.class);

    @Value("${web-client.max-buffer-size}")
    private int maxBufferSize;

    @Value("${web-client.timeouts.read}")
    private int readTimeout;

    @Value("${web-client.timeouts.connection}")
    private int connectionTimeout;

    private WebClient webClient;

    public BasicReadWebClient() {
    }

    @PostConstruct
    private void initializeWebClient() {
        var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                .responseTimeout(Duration.ofMillis(readTimeout))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS)));

        final var strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(maxBufferSize))
                .build();

        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .build();
    }

    public <T> T getData(String uri, ParameterizedTypeReference<T> typeReference) throws DataSourceAccessException {
        try {
            return webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(typeReference)
                    .block();
        } catch (Exception exception) {
            LOGGER.error("Unable to retrieve data", exception);
            throw new DataSourceAccessException(exception);
        }
    }

}
