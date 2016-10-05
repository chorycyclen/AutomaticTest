package com.cook.selenium.dataproviders;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class CSVDataProvider {

    @DataProvider(name = "test1",parallel = true)
    public static Iterator<Object[]> createData(Method method) throws Exception {
        //打印方法名
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        System.out.println(method.getName());
        //读取测试数据

        Reader reader = new FileReader(CSVDataProvider.class.getResource("/").getPath()
                + File.separator + "test_data_files" + File.separator + method.getName() + ".csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
        for (CSVRecord record :
                records) {
            String[] values = {record.get(0), record.get(1)};
            data.add(values);
        }
        return data.iterator();
    }
}
