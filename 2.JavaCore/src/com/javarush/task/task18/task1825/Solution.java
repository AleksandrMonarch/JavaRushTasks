package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) {

        List<String> fileParts = new ArrayList<>();
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (consoleReader.ready()) {
                String line = consoleReader.readLine();
                if ("end".equals(line)) break;
                fileParts.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(fileParts);
        List<String> buffer = new ArrayList<>();
        String resultFileName = fileParts.get(0).substring(0, fileParts.get(0).lastIndexOf("."));

        for (String filePart : fileParts) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(filePart))) {
                while (fileReader.ready()) {
                    buffer.add(fileReader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(resultFileName))) {
            for (String bufferLine : buffer) {
                fileWriter.write(bufferLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
