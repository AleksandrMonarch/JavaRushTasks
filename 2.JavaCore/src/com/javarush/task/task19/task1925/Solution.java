package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) {

        String inputFileName = args[0];
        String outputFileName = args[1];

        ArrayList<String> temp = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
            while (fileReader.ready()) {
                temp.add(fileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : temp) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (word.length() > 6) stringBuilder.append(word).append(",");
            }
        }

        String result = stringBuilder.substring(0, stringBuilder.lastIndexOf(","));

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            fileWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
