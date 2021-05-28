package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;

/* 
Замена знаков
*/

public class Solution {
    public static void main(String[] args) {

        String inputFileName = " ";
        String outputFileName = " ";

        ArrayList<String> fileLines = new ArrayList<>();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputFileName = consoleReader.readLine();
            outputFileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
            while (fileReader.ready()) {
                fileLines.add(fileReader.readLine().replaceAll("[.]", "!"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String line : fileLines) {
                fileWriter.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
