package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public  static  WebDriver driver;
	public Logger logger;
	public Properties p; // for reading value from config.propertires
	  
	    @BeforeClass (groups={"sanity","regression","master"})
	    @Parameters({"os","browser"})
	public	void setup(String os, String br) throws IOException
		{
	    	//Loading config.properties file
	    FileReader	file= new FileReader("./src//test//resources//config.properties");
	     p=new Properties();
	    	p.load(file);
	    	logger=LogManager.getLogger(this.getClass()); // this will always represent the class
	    	 if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	         {
	    		 DesiredCapabilities capablities=new   DesiredCapabilities ();
	    		 if(os.equalsIgnoreCase("windows"))
	    		 {
	    		 capablities.setPlatform(Platform.WIN10);
	    		 }
	    		 else if(os.equalsIgnoreCase("mac"))
	    		 {
	    			 capablities.setPlatform(Platform.MAC);
	    		 }
	    		 else
	    		 {
	    			 System.out.println("No match os");
	    			 return;
	    		 }
	    		 
	    		 switch(br.toLowerCase())
	    		 {
	    		 
	    		 case "chrome": capablities.setBrowserName("chrome"); break;
	    		 case "edge":capablities.setBrowserName(" MicrosoftEdge "); break;
	    		 default:System.out.println("No matching browser"); return;
	    		 
	    		 }
	    		 driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capablities);
	    		 
	    	 }
	    	 if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	    	 {
	    		 switch(br.toLowerCase())
	 	    	{
	 	    	case "chrome" :driver=new ChromeDriver(); break;
	 	    	case "firefox" :driver=new FirefoxDriver();break;
	 	    	case"edge":driver=new EdgeDriver(); break;
	 	    	default :System.out.println("Invalid browser name"); return;
	 	    	} 
	    	 }
	    	
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("Appurl"));// reading url from properties file 
			driver.manage().window().maximize();
		}
	    @AfterClass(groups={"sanity","regression","master"})
	    public	void closingapp()
		{
			driver.quit();
		}
	  //Rendomly genrating values
		 
		public String randomSting()
		{
			String genratedstring =RandomStringUtils.randomAlphabetic(5);
			return  genratedstring;
		}
		
		 public String randomnumberic()
		 {
			String genratednumber= RandomStringUtils.randomNumeric(10);
			 return genratednumber;
		 }
		 public String randomalphanumaric()
		 {
			 String genratedstring = RandomStringUtils.randomAlphabetic(5);
			 String genratednumber=  RandomStringUtils.randomNumeric(10);
			 return (genratedstring +genratednumber);
		 }
		 
		public String  captureScreen(String tname ) //we need to change webdriver to static
		 {
			 String timeStamp =new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
			     TakesScreenshot takesceenshot=( TakesScreenshot) driver;
			    File sourceFile  = takesceenshot.getScreenshotAs(OutputType.FILE);
			    String targetFilePath = System.getProperty("user.dir")+"\\screenshots" + tname +"-" + timeStamp + ".png";
			 File  targetFile =  new File(targetFilePath );
			 sourceFile.renameTo(targetFile);
			 return targetFilePath ;
		 }
		 }

