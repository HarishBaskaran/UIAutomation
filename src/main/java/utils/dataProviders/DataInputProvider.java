package utils.dataProviders;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider {

	public static String[][] getSheet(String dataSheetName, int sheetNumber) {
		
		String[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(new File("./data/" + dataSheetName + ".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
			DataFormatter formatter = new DataFormatter();

			// get the number of rows
			int rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];

			// loop through the rows
			for (int i = 1; i < rowCount + 1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columnCount; j++) { // loop through the columns
						try {
							String cellValue = "";
							try {
//								cellValue = row.getCell(j).getStringCellValue();
								Cell cell = sheet.getRow(i).getCell(j);
								cellValue = formatter.formatCellValue(cell);
							} catch (NullPointerException e) {

							}

							data[i - 1][j] = cellValue; // add to the data array
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Loop through all rows
//		for (int i = 0; i < data.length; i++) {
//			for (int j = 0; j < data[i].length; j++)
//				System.out.print(data[i][j] + " ");
//			System.out.println("");
//		}
		return data;

	}

}
