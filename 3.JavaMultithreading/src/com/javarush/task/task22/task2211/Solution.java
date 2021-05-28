package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) throw new InvalidClassException("no arguments in main");

        Charset windows1251 = Charset.forName("Windows-1251");
        Charset utf8 = Charset.forName("UTF-8");

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), windows1251));
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), utf8))) {
            ArrayList<String> fileLines = new ArrayList<>();
            while (fileReader.ready()) {
                fileLines.add(fileReader.readLine());
            }

            for (String line : fileLines) {
                fileWriter.write(line);
            }
        }
    }
}
