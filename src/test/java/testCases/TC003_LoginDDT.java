package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider ="LoginData" ,dataProviderClass = DataProvider.class )
	public void verfiying_login(String email, String pwd, String exp)
	{
		
		try
		{
	 // home page
		HomePage h1=new HomePage(driver);
		h1.clickMyAccount();
		h1.clickLogin();
		
		// login page
		LoginPage lp=new LoginPage(driver);
		lp.set_username(email);
		lp.set_password(pwd);
		lp.click_submit();
		
		
		// My account page
		
		 MyAccountPage my=new  MyAccountPage(driver);
		boolean targetPage = my.isMYaccountPageExist();
		
		if(exp.equalsIgnoreCase("valid"))//data is valid username and password
		{
			if(targetPage==true)// login is successfull  h
			{
				my.click_logout();
				Assert.assertTrue(true);//vaild useranme valid password but login successfull  test case pass
				
			}
			else
			{
				Assert.assertTrue(false); // vaild useranme valid password but login fail here test case fail
			}
		}
		
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)// login is successfull with invalid username and password here test case fail
			{
				my.click_logout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(false); // invalid useranme invalid password and login fail here test case pass
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		 
		}

}
