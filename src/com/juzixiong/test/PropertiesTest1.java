package com.juzixiong.test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest1 {
    public static void main(String[] args) {

        //Properties 作为Map集合的操作
        //1.创建Properties对象
        Properties prop = new Properties();

        //添加数据
        prop.put("aaa" , "111");
        prop.put("bbb" , "222");
        prop.put("ccc" , "333");
        prop.put("ddd" , "444");

        //遍历
        Set<Object> keys = prop.keySet();
        for (Object key : keys) {
            Object value = prop.get(key);
            System.out.println(key + "=" + value);
        }

        Set<Map.Entry<Object, Object>> entries = prop.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }
}
