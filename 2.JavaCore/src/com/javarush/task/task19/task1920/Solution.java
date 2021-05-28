package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) {

        String fileName = args[0];
        ArrayList<String> fileContent = new ArrayList<>();
        Map<String, Double> nameSalaryMap = new TreeMap<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                fileContent.add(fileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : fileContent) {
            String[] nameSalary = line.split(" ");
            String name = nameSalary[0];
            double salary = Double.parseDouble(nameSalary[1]);

            if (nameSalaryMap.containsKey(name)) nameSalaryMap.put(name, nameSalaryMap.get(name) + salary);
            else nameSalaryMap.put(name, salary);
        }

        ArrayList<Double> values = new ArrayList<>(nameSalaryMap.values());
        Collections.sort(values);
        Collections.reverse(values);
        for (Map.Entry<String, Double> pair : nameSalaryMap.entrySet()) {
            if (pair.getValue().equals(values.get(0))) System.out.println(pair.getKey());
        }
    }
}
