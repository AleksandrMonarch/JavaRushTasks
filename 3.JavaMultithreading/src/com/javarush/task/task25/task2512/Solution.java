package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        t.interrupt();

        Stack<String> strings = new Stack<>();
        while (e != null) {
            strings.push(e.toString());
            e = e.getCause();
        }
        while (!strings.empty()) {
            System.out.println(strings.pop());
        }
    }

    public static void main(String[] args) throws Throwable {

        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
        throw new Throwable("A", new Exception("B", new RuntimeException("C")));
    }
}
