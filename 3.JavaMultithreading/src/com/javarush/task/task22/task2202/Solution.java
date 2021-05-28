package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

public class Solution {
    public static void main(String[] args) {
        try {
            System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
        } catch (TooShortStringException e) {
            System.out.println("Exception!");
            e.printStackTrace();
        }
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        String[] strings = string.split("\\s");
        if (strings.length < 5) throw new TooShortStringException();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            stringBuilder.append(strings[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
