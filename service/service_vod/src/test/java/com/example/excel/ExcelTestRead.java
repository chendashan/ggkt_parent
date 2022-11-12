package com.example.excel;

import com.alibaba.excel.EasyExcel;

public class ExcelTestRead {

    public static void main(String[] args) {
        String fileName = "D:\\MyCode\\IdeaProject\\other\\test.xlsx";
        EasyExcel.read(fileName, TestUser.class, new ExcelListener()).sheet().doRead();
    }
}
