package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        Pattern pattern = Pattern.compile("\\t");
        Matcher matcher = pattern.matcher(string);

        int tabCounter = 0;
        ArrayList<Integer> indexes = new ArrayList<>();

        while (matcher.find()) {
            indexes.add(matcher.start());
            tabCounter++;
        }
        if (tabCounter < 2) throw new TooShortStringException();
        return string.substring(indexes.get(0) + "\\n".length() - 1, indexes.get(1));
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
