package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//loctor
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFname;
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement textLname;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmpassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	  
	
	   //action
	public void setFname(String Fname)
	{
		txtFname.sendKeys(Fname );
	}
	public void setLname(String Lname)
	{
		textLname.sendKeys(Lname );
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setPhoneNumber(String phone)
	{
		txtPhone.sendKeys(phone);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPassword(String confirmpwd)
	{
		txtConfirmpassword.sendKeys(confirmpwd);
	}
	public void setPrivacyPloicy()
	{
		chkPolicy.click();
	}
	public void clickcontinue()
	{
		//sol1
		btnContinue.click();
		//sol2
	  // Actions act=new Actions(driver);
	  // act.moveToElement(btnContinue).click().perform();
		
	}
	public String confirmMeassge()
	{
		try {
			
			return (msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
		
	}
	
}
