package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream streamOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(streamOut);
        String[] strings = outputStream.toString().split("\\n");
        for (int i = 0; i < strings.length; i++) {
            if (i != 0 && i % 2 == 0) {
                System.out.println("JavaRush - курсы Java онлайн");
            }
            System.out.println(strings[i]);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
