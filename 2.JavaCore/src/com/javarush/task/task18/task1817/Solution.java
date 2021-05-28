package com.javarush.task.task18.task1817;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {

    private static int spaceCounter = 0;
    private static int symbolCounter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String line = reader.readLine();
            char[] charLineArray = line.toCharArray();

            for (char c : charLineArray) {
                if (c == ' ') {
                    spaceCounter++;
                }
                symbolCounter++;
            }
        }
        reader.close();

        float result = (float) spaceCounter / symbolCounter * 100;
        System.out.printf("%.2f", result);
    }
}
