package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String fileName = "";
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintStream console = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        console.print(outputStream.toString());
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(outputStream.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(console);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

