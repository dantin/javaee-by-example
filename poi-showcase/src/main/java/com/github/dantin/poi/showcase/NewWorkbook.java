package com.github.dantin.poi.showcase;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Show how to create a new workbook.
 *
 * @version 1.0 2018-06-20
 */
public class NewWorkbook {
    public static void main(String[] args) {
        Workbook wb = new XSSFWorkbook();  // or new HSSFWorkbook();

        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
