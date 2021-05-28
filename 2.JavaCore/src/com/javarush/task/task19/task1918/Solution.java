package com.javarush.task.task19.task1918;

import java.io.*;
import java.util.ArrayList;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) {
        String tag = args[0];
        ArrayList<String> result = new ArrayList<>();
        StringBuilder fileName = new StringBuilder();
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (consoleReader.ready()) {
                fileName.append(consoleReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName.toString()))) {
            while (fileReader.ready()) {
                fileContent.append(fileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String startTag = "<" + tag;
        String endTag = "</" + tag;

        int statIndex = 0;
        int endIndex;
        int innerIndex;

        while ((statIndex = fileContent.indexOf(startTag, statIndex)) != -1) {
            endIndex = fileContent.indexOf(endTag, statIndex);
            innerIndex = statIndex + startTag.length();

            while (fileContent.substring(innerIndex, endIndex).contains(startTag)) {
                innerIndex = fileContent.indexOf(startTag, innerIndex) + startTag.length();
                endIndex = fileContent.indexOf(endTag, endIndex + endTag.length());
            }

            result.add(fileContent.substring(statIndex, endIndex + endTag.length() + 1));
            statIndex += startTag.length();
        }
        for (String s : result) {
            System.out.println(s);
        }
    }
}
