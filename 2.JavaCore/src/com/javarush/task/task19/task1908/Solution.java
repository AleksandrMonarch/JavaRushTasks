package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) {

        String inputFileName = "";
        String outputFileName = "";

        ArrayList<String> allNumbers = new ArrayList<>();


        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));) {
            inputFileName = consoleReader.readLine();
            outputFileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                String[] lineWords = line.split("\\s+");
                for (String everyWord : lineWords) {
                    if (everyWord.matches("\\d+")) allNumbers.add(everyWord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))){
            for (String everyNumber : allNumbers) {
                writer.write(everyNumber + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
