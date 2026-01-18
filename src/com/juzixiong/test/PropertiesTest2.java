package com.juzixiong.test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest2 {
    public static void main(String[] args) throws IOException {

        //Properties  跟 IO流结合的操作
        //1.创建Properties对象
        Properties prop = new Properties();

        //添加数据
        prop.put("aaa" , "111");
        prop.put("bbb" , "222");
        prop.put("ccc" , "333");
        prop.put("ddd" , "444");

        //把集合中的数据以键值对形式写入文件
        FileOutputStream fos = new FileOutputStream("a.properties");
        prop.store(fos , "test");
        fos.close();




        /*//BufferedWriter
        BufferedWriter bw = new BufferedWriter(new FileWriter("a.properties"));
        Set<Map.Entry<Object, Object>> entries = prop.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "=" + value);
        }
        bw.close();*/


    }
}

