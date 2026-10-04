package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	// construction
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
   // locator
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_username;
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txt_password;
	@FindBy(xpath="//input[@value='Login']") 
	WebElement btn_submit;
	
	//Action
	public void set_username(String usernme)
	{
		txt_username.sendKeys(usernme);
	}
	public void set_password(String password)
	{
		txt_password.sendKeys(password);
	}
	 public void click_submit()
	 {
		 btn_submit.click();
		 
	 }
	 
	
}
