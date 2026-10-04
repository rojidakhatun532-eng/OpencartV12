package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
         //construction 
	 public  HomePage(WebDriver driver )
       {
	       super(driver);
       }
	   
	   //locator 
	   
	   @FindBy(xpath ="//span[@class='caret']" )//span[@class='caret']
	   WebElement lnkMyaccount;
	   @FindBy(xpath ="//a[normalize-space()='Register']")
	   WebElement lnkRegistation;
	   @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	  WebElement lnkLogin;
	   
	   // Actions 
	   
	 public   void clickMyAccount()
	   {
		 lnkMyaccount.click();
	   }
	  public  void clickRegister()
	    {
		  lnkRegistation.click();
	    }
	  
	 public  void clickLogin()
	  {
		 lnkLogin.click();;
	  }
          
	 
          
}
