package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001AccountRegistrationTest extends BaseClass {
  
	@Test(groups={"regression", "master"})
	void varify_Account_Registration()
	{
		try
		{
		logger.info("***Starting Test case***");
		HomePage hp=new HomePage(driver);
	   hp.clickMyAccount();
	   logger.info("***Clicked on MyAccount link***");
		hp.clickRegister();
		logger.info("***clicked on Register link");
		
		AccountRegistrationPage Reg= new AccountRegistrationPage(driver);
		logger.info("***Here Im providing customer Details");
		Reg.setFname(randomSting().toUpperCase());// radnom alphabets
		Reg.setLname(randomSting().toUpperCase());
		Reg.setEmail(randomSting() + "@gmail.com");
		Reg.setPhoneNumber(randomnumberic());
		
	String 	password =randomalphanumaric();
		
		Reg.setPassword(password );
		Reg.setConfirmPassword(password );
		Reg.setPrivacyPloicy();
		Reg.clickcontinue();
		logger.info("**Validating the confirmation message**");
	String 	confirmation =Reg.confirmMeassge();
	
	   if(confirmation.equals("Your Account Has Been Created!"))
	   {
		   Assert.assertTrue(true);
	   }
	   else
	   {
		   logger.error("Test Fail..");
		   logger.debug("Debug logs..");
		   Assert.assertTrue(false);
	   }
	
		//Assert.assertEquals(confirmation, "Your Account Has Been Created!" );
			
		}
		catch(Exception e)
		{
		
			Assert.fail();
		}
		logger.info("**Test Finished**");
		
	}
	
}
