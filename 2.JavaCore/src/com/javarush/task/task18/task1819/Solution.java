package com.javarush.task.task18.task1819;

import java.io.*;
import java.util.ArrayList;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String file1Path = consoleReader.readLine();
        String file2Path = consoleReader.readLine();
        consoleReader.close();

        FileInputStream fis1 = new FileInputStream(file1Path);
        FileInputStream fis2 = new FileInputStream(file2Path);

        byte[] file1Bytes = new byte[fis1.available()];
        byte[] file2Bytes = new byte[fis2.available()];

        fis1.read(file1Bytes);
        fis2.read(file2Bytes);

        fis1.close();
        fis2.close();

        FileOutputStream fos = new FileOutputStream(file1Path);
        fos.write(file2Bytes);
        fos.write(file1Bytes);

        fos.close();
    }
}
