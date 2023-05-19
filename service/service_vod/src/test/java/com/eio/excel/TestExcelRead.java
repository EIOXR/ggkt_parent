package com.eio.excel;

import com.alibaba.excel.EasyExcel;

public class TestExcelRead {

    public static void main(String[] args) {
        String fileName = "D:\\TestExcelRead.xlsx";

        EasyExcel.read(fileName, User.class,new ExcelListener())
                .sheet()
                .doRead();
    }

}
