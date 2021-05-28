package com.javarush.task.task19.task1922;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        String fileName = "";
//        InputStream inputStream = new ByteArrayInputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt".getBytes());
//        System.setIn(inputStream);
        ArrayList<String > fileLines = new ArrayList<>();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                fileLines.add(fileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> resultLines = new ArrayList<>();

        for (String line : fileLines) {
            int wordCounter = 0;
            String[] lineWords = line.replaceAll("\\p{Punct}", "").split("\\s+");
            for (String word : lineWords) {
                if (wordCounter > 2) break;
                for (String expectedWord : words) {
                    if (word.equals(expectedWord)) {
                        wordCounter++;
                    }
                }
            }
            if (wordCounter == 2) resultLines.add(line);
        }

        for (String resultLine : resultLines) {
            System.out.println(resultLine);
        }
    }
}
