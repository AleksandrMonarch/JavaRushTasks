package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        String originalFileName = "";
        String updatedFileName = "";

        ArrayList<String > originalLines = new ArrayList<>();
        ArrayList<String > updatedLines = new ArrayList<>();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            originalFileName = consoleReader.readLine();
            updatedFileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader originalFileReader = new BufferedReader(new FileReader(originalFileName));) {
            while (originalFileReader.ready()) {
                originalLines.add(originalFileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader updatedFileReader = new BufferedReader(new FileReader(updatedFileName));) {
            while (updatedFileReader.ready()) {
                updatedLines.add(updatedFileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int originalIndex = 0;
        int updatedIndex = 0;

        while (originalIndex < originalLines.size() && updatedIndex < updatedLines.size()) {
            if (originalLines.get(originalIndex).equals(updatedLines.get(updatedIndex))) {
                lines.add(new LineItem(Type.SAME, originalLines.get(originalIndex)));
                originalIndex++;
                updatedIndex++;
            } else if (updatedIndex + 1 < updatedLines.size() && originalLines.get(originalIndex).equals(updatedLines.get(updatedIndex + 1))) {
                lines.add(new LineItem(Type.ADDED, updatedLines.get(updatedIndex++)));
            } else if (originalIndex + 1 < originalLines.size() && updatedLines.get(updatedIndex).equals(originalLines.get(originalIndex + 1))) {
                lines.add(new LineItem(Type.REMOVED, originalLines.get(originalIndex++)));
            }
        }

        while (originalIndex < originalLines.size()) {
            lines.add(new LineItem(Type.REMOVED, originalLines.get(originalIndex++)));
        }

        while (updatedIndex < updatedLines.size()) {
            lines.add(new LineItem(Type.ADDED, updatedLines.get(updatedIndex++)));
        }

        for (LineItem lineItem : lines) {
            System.out.println(lineItem.type + " " + lineItem.line);
        }
    }



    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
