package ca.poc.djj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestdataReader implements Serializable {
	private static final long serialVersionUID = 10153789456123L;

	
	public static String getPath() {
		String file = "testdata.xlsx";
	
		return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + file;

	}

	@SuppressWarnings("deprecation")
	public static Map<String, String> getDataFromFile(String detailInstanceName) {
		XSSFWorkbook myWorkBook = null;
		Map<String, String> testdata = null;
		try {
			String path = TestdataReader.getPath();
			File myFile = new File(path);
			FileInputStream fis = new FileInputStream(myFile);

			// Finds the workbook instance for XLSX file
			myWorkBook = new XSSFWorkbook(fis);

			XSSFSheet mySheet;
			// Return first sheet from the XLSX workbook
			mySheet = myWorkBook.getSheet("Testdata");
			

			
			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = mySheet.iterator();
			// Traversing over each row of XLSX file
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						cell.setCellType(1);
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						cell.setCellType(1);
						break;
					default:
					}
				}
			}
			int testdataRowindex = 0;
			String TestColumn = "NotAvailable";
			Iterator<Row> rowIterator2 = mySheet.iterator();
			while (rowIterator2.hasNext()) {
				Row row = rowIterator2.next();
				if (row.getCell(0).getStringCellValue().contains(detailInstanceName)) {
					testdataRowindex = row.getCell(0).getRowIndex();
					TestColumn = "Available";
					break;
				}

			}
			if (TestColumn.equalsIgnoreCase("NotAvailable")) {
				throw new RuntimeException("Data not supplied for " + detailInstanceName);
			}
			String[] firstRow = new String[1000];

			for (int colIndex = 0; colIndex <= mySheet.getRow(0).getLastCellNum() - 1; colIndex++) {
				firstRow[colIndex] = mySheet.getRow(0).getCell(colIndex).getStringCellValue();
			}

			String[] testdataRow2 = new String[1000];

			for (int colIndex = 0; colIndex <= mySheet.getRow(0).getLastCellNum() - 1; colIndex++) {
				try {
					testdataRow2[colIndex] = mySheet.getRow(testdataRowindex).getCell(colIndex).getStringCellValue();
				} catch (NullPointerException e) {
					testdataRow2[colIndex] = null;
				}
			}

			int i = 0;
			testdata = new HashMap<String, String>();
			for (String cellvalue : firstRow) {
				if (cellvalue == null || cellvalue.equalsIgnoreCase(""))
					break;
				try {
					testdata.put(cellvalue, testdataRow2[i++]);
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in fetching test data from Excel");
			e.printStackTrace();
		} finally {
			try {
				myWorkBook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return testdata;
	}
	
	public static void main(String args[]) {
		Map<String, String> test=getDataFromFile("Scenario3");
		System.out.println(test.get("Customer location"));
		
	}

}
