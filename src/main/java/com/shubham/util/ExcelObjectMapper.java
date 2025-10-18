package com.shubham.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class ExcelObjectMapper {

    public static <T> List<T> readExcel(String filePath, String sheetName, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in Excel.");
            }

            // Map column headers to column indexes
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    headerMap.put(cell.getStringCellValue().trim(), i);
                }
            }

            // Loop through all data rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                T obj = clazz.getDeclaredConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(CellName.class)) {
                        CellName annotation = field.getAnnotation(CellName.class);
                        String columnName = annotation.value();
                        Integer columnIndex = headerMap.get(columnName);

                        if (columnIndex != null) {
                            Cell cell = row.getCell(columnIndex);
                            if (cell != null) {
                                cell.setCellType(CellType.STRING); // Ensure we read as string
                                String value = cell.getStringCellValue();

                                field.setAccessible(true); // In case it's private
                                field.set(obj, value);
                            }
                        }
                    }
                }

                dataList.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
