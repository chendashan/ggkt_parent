package com.example.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class ExcelTestWrite {

    public static void main(String[] args) {
        String fileName = "D:\\MyCode\\IdeaProject\\other\\test.xlsx";

        EasyExcel.write(fileName, TestUser.class)
                .sheet("001")
                .doWrite(data());
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<TestUser> data() {
        List<TestUser> list = new ArrayList<TestUser>();
        for (int i = 0; i < 10; i++) {
            TestUser data = new TestUser();
            data.setId(i);
            data.setName("张三" + i);
            list.add(data);
        }
        return list;
    }

}
