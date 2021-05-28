package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {

    public static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {

        String fileName = "";

//        InputStream stream = new ByteArrayInputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt".getBytes());
//        System.setIn(stream);

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                for (Map.Entry<Integer, String> pair : map.entrySet()) {
                    line = line.replaceAll("\\b" + pair.getKey() + "\\b", pair.getValue());
                }
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String resultLine : result) {
            System.out.println(resultLine);
        }
    }
}
