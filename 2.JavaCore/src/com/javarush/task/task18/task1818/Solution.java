package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String resultFilePath = consoleReader.readLine();
        String inputFilePath1 = consoleReader.readLine();
        String inputFilePath2 = consoleReader.readLine();

        consoleReader.close();
//
//        String resultFilePath = "C:\\Users\\Aleksandr\\Desktop\\resultFile.txt";
//        String inputFilePath1 = "C:\\Users\\Aleksandr\\Desktop\\inputFile1.txt";
//        String inputFilePath2 = "C:\\Users\\Aleksandr\\Desktop\\inputFile2.txt";

        FileOutputStream fos = new FileOutputStream(resultFilePath);
        FileInputStream fis1 = new FileInputStream(inputFilePath1);
        FileInputStream fis2 = new FileInputStream(inputFilePath2);

        byte[] file1bytes = new byte[fis1.available()];
        byte[] file2bytes = new byte[fis2.available()];

        fis1.read(file1bytes);
        fis2.read(file2bytes);

        fos.write(file1bytes);
        fos.write(file2bytes);

        fis1.close();
        fis2.close();
        fos.close();;
    }
}
