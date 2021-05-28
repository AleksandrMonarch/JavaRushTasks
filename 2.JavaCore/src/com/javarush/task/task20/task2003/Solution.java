package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* 
Знакомство с properties
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        //напишите тут ваш код
        Properties properties = new Properties();
        properties.putAll(runtimeStorage);
        properties.store(outputStream, null);
    }

    public static void load(InputStream inputStream) throws IOException {
        //напишите тут ваш код
        Properties properties = new Properties();
        properties.load(inputStream);
        Set<Object> objects = properties.keySet();
        for (Object object : objects) {
            if (object instanceof String) {
                String key = (String) object;
                runtimeStorage.put(key, properties.getProperty(key));
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine())) {
            load(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }
}
