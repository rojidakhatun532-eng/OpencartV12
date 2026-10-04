package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener
        {
	
		public ExtentSparkReporter sparkReporter;// UI of the reprt
		public ExtentReports extent; //populate commmon infomation on the report scuh as Tester info,OS info ectc
		public ExtentTest test;// creating test case entries in the report and update status of the method
		String repName ;
		
	          public void onStart(ITestContext testcontext)// creating UI
	          	{
	        	  
	        String timeStamp =new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());// time stamp when we make history of report then we stamp time 
	         repName="Test-Report-" +timeStamp +".html"; //here dynamic report will genrate
	    	 sparkReporter=new ExtentSparkReporter(".\\reports\\" +repName);// spicify the location of the reports
	    	 
	    	 sparkReporter.config().setDocumentTitle(" OpenCart Automation Report");// title of report
	    	 sparkReporter.config().setReportName("OpenCart Functional Testing");// name of the report
	    	 sparkReporter.config().setTheme(Theme.DARK);
	    	 
	    	 extent=new ExtentReports();
	    	 extent.attachReporter(sparkReporter);
	    	 extent.setSystemInfo("Application", "OpenCart");
	    	 extent.setSystemInfo("Module", "Admin");
	    	 extent.setSystemInfo("Sub Module", "Customer");
	    	 extent.setSystemInfo("User Name", System.getProperty("user.name ")); // current user of the sytsem
	    	 extent.setSystemInfo("Environment", "QA");
	  
	    	String  os =testcontext.getCurrentXmlTest().getParameter("os");
	    	 extent.setSystemInfo("Operating System", os);// current OS
	    	 
	    	 String  broswer =testcontext.getCurrentXmlTest().getParameter("browser");
	    	 extent.setSystemInfo("Browser", broswer);// current Browser
	    	 
	    	 List <String>includedGroups =testcontext.getCurrentXmlTest().getIncludedGroups();
	    	  if(!includedGroups.isEmpty())
	    	  {
	    	 extent.setSystemInfo("Groups", includedGroups.toString());// current Browser
	    	  }
	    	 
	    	 
		  }
	          
		public void onTestSuccess(ITestResult result) // it will trigger when test method get pass
		{ 
			test= extent.createTest(result.getTestClass().getName());// create a new entry in the report // it will give class name in the reports
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS, "Test case PASSED is:" + result.getName());//update of the report
			   
			  }

		 
		 
	      public void onTestFailure(ITestResult result) 
	      {
	    	  test= extent.createTest(result.getTestClass().getName());
	    	  test.assignCategory(result.getMethod().getGroups());
	    	  
			    test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
			    test.log(Status.FAIL, "Test case FAILED is:" + result.getThrowable().getMessage());
			    
			    try
			    {
			    
			    String imgPath =new BaseClass().captureScreen(result.getName());// here we need to change driver to static in Base class
			    test.addScreenCaptureFromPath(imgPath);
			    }
			    catch(Exception e)
			    {
			    	e.printStackTrace();
			    }
			  }
		 public  void onTestSkipped(ITestResult result) 
		 {
			 test= extent.createTest(result.getTestClass().getName());
	    	  test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, "Test case SKIP is:" + result.getName());
			  test.log(Status.SKIP, "Test case skip is:" + result.getThrowable().getMessage());

		 }
			  
		 public void onFinish(ITestContext context) // THIS method is mendatory it give in the report what above we mention
		 {
			    extent.flush();
			    
			    // open the reports automatically 
			   String pathOfExtentReport= System.getProperty("user.dir") + "\\reports\\"+ repName ;  
			    File   extentReport  =new File(pathOfExtentReport);
			    try
			    {
			     Desktop.getDesktop().browse(extentReport.toURI());
			    }
			    catch(Exception e)
			    {
			    	e.printStackTrace();
			    }
			     
			  }
		 
		 
		 
		 
	}
		

