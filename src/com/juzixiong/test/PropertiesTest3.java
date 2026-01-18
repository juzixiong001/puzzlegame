package com.juzixiong.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest3 {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("a.properties");
        //读取文件数据
        prop.load(fis);

        Set<Map.Entry<Object, Object>> entries = prop.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }

        fis.close();

    }
}
