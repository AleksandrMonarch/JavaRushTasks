package com.javarush.task.task19.task1907;

import java.io.*;

/* 
Считаем слово
*/

public class Solution {

    private static int worldCounter;

    public static void main(String[] args) {

        String inputFileName = "";

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputFileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                line = line.replaceAll("\\W", " ");
                line = line.trim();
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    if ("world".equals(word)) worldCounter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(worldCounter);
    }
}
