/*
 * Open a file excel and get data in it.
 */

package ultilities;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelConnect {

	String filePath;
	Sheet sh;

	public ExcelConnect(String sheetName) {
		 try {
			filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AgileTravel_TestData.xlsx";
		} catch (Exception e) {
			e.printStackTrace();
		}
		 	// Open file - workbook
			File testDataFile = new File(filePath);
			Workbook wb = null;
			try {
				wb = WorkbookFactory.create(testDataFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 sh = wb.getSheet(sheetName);
	}

	// Get test data from test data sheet in hashmap based on row number
	public HashMap<String, String> getTestDataInMap(int rowNum) throws Exception {
		// Read data row by row and put in map
		HashMap<String, String> hm = new HashMap<String, String>();
		
		for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
			String value;
			if(sh.getRow(rowNum).getCell(i) != null) {
				sh.getRow(rowNum).getCell(i);
				value = sh.getRow(rowNum).getCell(i).toString();			
			}
			else {
				value = "";
			}
			hm.put(sh.getRow(0).getCell(i).toString(), value);
		}	
		return hm;
	}

	// Get row count
	public int getRowCount() {		
		return sh.getLastRowNum();
	}
	
	// Get column count
	public int getColCount() {
		return sh.getRow(0).getLastCellNum();
	}
}
