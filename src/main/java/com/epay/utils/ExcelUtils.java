package com.epay.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.epay.ls.base.LuckyScratchBase;
import com.epay.ls.library.LuckyScratchPage;

public class ExcelUtils extends LuckyScratchBase {

	static String TESTDATA_SHEET_PATH = properties.getProperty("DataFile");
	static Workbook workbook;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		DataFormatter dataformatter = new DataFormatter();
		Object[][] data = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
			workbook = WorkbookFactory.create(file);
			sheet = workbook.getSheet(sheetName);
			data = new Object[(sheet.getLastRowNum())][sheet.getRow(0).getLastCellNum()];
			System.out.println("sheet.getLastRowNum() " + sheet.getLastRowNum());
			System.out.println("sheet.getRow(0).getLastCellNum() " + sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < (sheet.getLastRowNum()); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					dataformatter = new DataFormatter();
					data[i][k] = dataformatter.formatCellValue(sheet.getRow(i + 1).getCell(k));
					System.out.println(data[i][k]);
				}
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setCellData(int columnNo, String output, String name, String currency, String errDesc, String emailID,
			String cc, String amount) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		XSSFWorkbook workbooks = null;
		XSSFSheet sheets = null;
		Row rows = null;
		Cell cells = null;
		String callback = null;
		String[] responsedetails = output.split("[^\\w]+");
		callback = LuckyScratchPage.getCallBackStatus(responsedetails[1]);
		String[] response = { responsedetails[1], amount, cc, responsedetails[2], errDesc, emailID, name, currency,
				callback };
		try {
			fi = new FileInputStream(properties.getProperty("outputFile"));
			workbooks = new XSSFWorkbook(fi);
			sheets = workbooks.getSheet("sheet2");
			rows = sheets.createRow(columnNo);
			for (int i = 0; i < response.length; i++) {
				cells = rows.createCell(i + 1);
				cells.setCellValue(response[i]);
			}
			fo = new FileOutputStream(properties.getProperty("outputFile"));
			workbooks.write(fo);
			fo.close();
		} catch (IOException io) {

		}

	}

	/*
	 * public void setCellData(int i, String output, String name, String
	 * currency, String errDesc) throws IOException { FileInputStream fi = new
	 * FileInputStream(properties.getProperty("outputFile")); workbooks = new
	 * XSSFWorkbook(fi); sheets = workbooks.getSheet("sheet2"); rows =
	 * sheets.createRow(i); cells = rows.createCell(1);
	 * cells.setCellValue(name); cells = rows.createCell(2);
	 * cells.setCellValue(currency); String[] responsedetails =
	 * output.split("[^\\w]+"); cells = rows.createCell(3);
	 * cells.setCellValue(responsedetails[1]); cells = rows.createCell(4);
	 * cells.setCellValue(responsedetails[2]); cells = rows.createCell(5);
	 * cells.setCellValue(errDesc); FileOutputStream fo = new
	 * FileOutputStream(properties.getProperty("outputFile"));
	 * workbooks.write(fo); fo.close();
	 * 
	 * }
	 */

}
