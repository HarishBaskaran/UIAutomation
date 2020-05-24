package utils.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RuntimeTracker_Singleton {
	
	private static final RuntimeTracker_Singleton inst = new RuntimeTracker_Singleton();

	private RuntimeTracker_Singleton() {
		super();
	}

	public static RuntimeTracker_Singleton getInstance() {
		return inst;
	}

	// Create blank workbook
	XSSFWorkbook workbook = new XSSFWorkbook();

	// Create a blank sheet
	XSSFSheet spreadsheet = workbook.createSheet(" Status ");

	// Create row object
	XSSFRow row;
	static int rowid = 0;

	BufferedWriter buf = null;
	FileOutputStream out = null;

	public synchronized void writeResults(Object[] objectArr) {

		row = spreadsheet.createRow(rowid++);
		int cellid = 0;

		for (Object obj : objectArr) {
			Cell cell = row.createCell(cellid++);
			cell.setCellValue((String) obj);
		}

		// Write the workbook in file system

		try {
			out = new FileOutputStream(new File(System.getProperty("user.dir") + "/RunTimeTracker.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized void close() {
		try {
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Writesheet.xlsx written successfully");
	}
}
