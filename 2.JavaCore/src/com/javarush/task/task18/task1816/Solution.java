package com.javarush.task.task18.task1816;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Английские буквы
*/

public class Solution {

    private static int counter = 0;

    private final static String englishLowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final static String englishUppercaseLetters = englishLowercaseLetters.toUpperCase();
    private final static String englishLetters = englishLowercaseLetters.concat(englishUppercaseLetters);

    private final static char[] englishLettersCharArray = englishLetters.toCharArray();

    public static void main(String[] args) throws IOException {

        if (args.length != 0 && args[0] != null) {
            String filePath = args[0];
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> linesList = new ArrayList<>();

            while (reader.ready()) {
                linesList.add(reader.readLine());
            }

            reader.close();

            for (String s : linesList) {
                char[] lineChars = s.toCharArray();
                for (char c : lineChars) {
                    for (char c1 : englishLettersCharArray) {
                        if (c == c1) counter++;
                    }
                }
            }
            System.out.println(counter);
        }
    }
}
