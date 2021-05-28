package com.javarush.task.task18.task1824;

import java.io.*;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            while (consoleReader.ready()) {
                String fIleName = consoleReader.readLine();
                try (FileInputStream fis = new FileInputStream(fIleName)) {

                } catch (FileNotFoundException e) {
                    System.out.println(fIleName);
                    consoleReader.close();
                    System.exit(-1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
