package com.banking;


import com.banking.controller.ATMController;
import com.banking.model.ATM;
import com.banking.util.BasicReadWebClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.junit.Assert.assertTrue;


@SpringBootTest

class OpenBankingApplicationTests {




	@Test
	void contextLoads() {
	}



	@Autowired
	private ATMController atmController;
	

	//Test the controller
	//Verify response when no value is set for identification
	@Test
	public void AtmFinderWithNoIdentification() throws Exception {
		System.out.println("here");
		List atm = atmController.getATMByIdentifier(" ");
		assertTrue(atm.isEmpty());

	}

	//Verify the size of the list which should always be 1
	//Assert the returned identification id
	@Test
	public void AtmFinderCount() throws Exception {
		List<ATM> atm = atmController.getATMByIdentifier("LFFFBC11");
		assertTrue(atm.size()==1);
		assertTrue(atm.get(0).getIdentification().equals("LFFFBC11"));
	}

}





