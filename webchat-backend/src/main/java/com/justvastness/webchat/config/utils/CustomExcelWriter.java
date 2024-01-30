package com.justvastness.webchat.config.utils;

import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.cell.CellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * ExcelWriter
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/6/4 4:15 下午
 **/
public class CustomExcelWriter extends ExcelWriter {

    public CustomExcelWriter(boolean isXlsx, String sheetName) {
        super(isXlsx, sheetName);
    }

    public void writeCellValue(int x, int y, Object value, CellStyle cellStyle) {
        final Cell cell = getOrCreateCell(x, y);
        CellUtil.setCellValue(cell, value, cellStyle);
    }
}
