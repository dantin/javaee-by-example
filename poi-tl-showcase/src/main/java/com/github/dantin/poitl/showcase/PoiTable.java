package com.github.dantin.poitl.showcase;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.IOException;

public class PoiTable {
    public static void main(String[] args) throws IOException {

        XWPFDocument doc = new XWPFDocument(PoiTable.class.getResourceAsStream("templates/template1.docx"));

        int noRows = 5;
        int noCols = 7;
        XWPFTable table = doc.createTable(noRows, noCols);

        for (XWPFTableRow row : table.getRows()) {
            for (int i = 0; i < noCols; i++) {
                XWPFTableCell xwpfCell = row.getCell(i);
            }
        }

        doc.close();
    }
}
