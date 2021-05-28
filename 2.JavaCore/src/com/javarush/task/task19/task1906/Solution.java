package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {

        ArrayList<Character> buffer = new ArrayList<>();
        String inputFileName = null;
        String outputFileName = null;

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputFileName = consoleReader.readLine();
            outputFileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (FileReader reader = new FileReader(inputFileName)) {
                while (reader.ready()) {
                    buffer.add((char) reader.read());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (int i = 1; i < buffer.size(); i += 2) {
                writer.write(buffer.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
