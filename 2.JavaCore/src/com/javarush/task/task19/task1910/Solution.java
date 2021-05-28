package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) {

        String inputFileName = "";
        String outputFileName = "";

        ArrayList<String> resultFileLines = new ArrayList<>();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputFileName = consoleReader.readLine();
            outputFileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName))) {
            while (fileReader.ready()) {
                resultFileLines.add(fileReader.readLine().replaceAll("\\p{Punct}", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFileName)) ) {
            for (String resultFileLine : resultFileLines) {
                fileWriter.write(resultFileLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
