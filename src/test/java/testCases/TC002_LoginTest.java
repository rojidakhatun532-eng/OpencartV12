package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity", "master"})
	public void verfiying_login()
	{
		logger.info("***Stating TC002_LoginTest***");
		try
		{
	 // home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		// login page
		LoginPage lc=new LoginPage(driver);
		lc.set_username(p.getProperty("username"));
		lc.set_password(p.getProperty("pass"));
		lc.click_submit();
		
		// My account page
		
		 MyAccountPage my=new  MyAccountPage(driver);
		boolean targetPage = my.isMYaccountPageExist();
		 Assert.assertEquals(targetPage, true);
		 
		}
		catch(Exception e)
		{
			Assert.fail();
		}
				 
		logger.info("***Finsih TC002_LoginTest***");
	}

}
