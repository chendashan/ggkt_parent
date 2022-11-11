package com.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TestUser {

    @ExcelProperty("用户编号")
    private int id;

    @ExcelProperty("用户名称")
    private String name;

}
