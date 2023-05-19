package com.eio.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestExcelWrite {

    public static void main(String[] args) {

        //设置文件名称和路径
        String path = "D:\\TestExcelWrite.xlsx";

        EasyExcel.write(path, User.class)
                .sheet("测试EasyExcel写操作")
                .doWrite(data());
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("张三" + i);
            list.add(data);
        }
        return list;
    }
}
