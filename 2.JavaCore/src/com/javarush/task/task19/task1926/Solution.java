package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) {

        StringBuilder fileName = new StringBuilder();
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName.append(consoleReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName.toString()))) {
            while (fileReader.ready()) {
                StringBuilder fileLine = new StringBuilder(fileReader.readLine());
                System.out.println(fileLine.reverse().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
