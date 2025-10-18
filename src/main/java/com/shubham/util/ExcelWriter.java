package com.shubham.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

    public class ExcelWriter {

        public static void writeToCell(String filePath, String sheetName, int rowIndex, int columnIndex, String value) {
            FileInputStream fis = null;
            FileOutputStream fos = null;

            try {
                File file = new File(filePath);
                fis = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    sheet = workbook.createSheet(sheetName);
                }

                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }

                Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellValue(value);

                // Close input stream before writing
                fis.close();

                fos = new FileOutputStream(file);
                workbook.write(fos);

                System.out.println("✅ Written '" + value + "' to " + sheetName + " row " + rowIndex + ", col " + columnIndex);

                workbook.close();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) fis.close();
                    if (fos != null) fos.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
    }

