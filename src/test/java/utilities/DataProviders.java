package utilities;


import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException 
	{

	    String path = ".\\testData\\LoginTestData.xlsx";// taking excel file from testData

	    ExcelUtils xlutil=new ExcelUtils(path);//creating object for ExcelUtils

	    int totalrows = xlutil.getRowCount("Sheet1");
	    int totalcols = xlutil.getCellCount("Sheet1",1);

	    String  logindata[][] = new String [totalrows][totalcols];// creating two dimensnal array

	    for (int i = 1; i <= totalrows; i++) //1 // read the data from xl file storing in two deminsional array
	    {

	        for (int j = 0; j < totalcols; j++) //0 // i is rows and j is colnum
	        {

	            logindata[i-1][j] = xlutil.getCellDatat("Sheet1",i, j);//1,0
	        }
	    
	    }
	    return logindata;// returing two demension araay
	}
}
