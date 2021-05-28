package com.javarush.task.task18.task1820;

import java.io.*;
/*
Округление чисел
*/


public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFilePath = consoleReader.readLine();
        String outputFilePath = consoleReader.readLine();
        consoleReader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFilePath));

        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] nums = line.split(" ");
            for (String num : nums) {
                fileWriter.write(String.valueOf(Math.round(Float.parseFloat(num))) + " ");
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}

