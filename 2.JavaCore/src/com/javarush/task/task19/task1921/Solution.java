package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
//        String fileName = "C:\\Users\\Aleksandr\\Desktop\\originalFile.txt";
        String fileName = args[0];
        ArrayList<String> fileLines = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                fileLines.add(fileReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : fileLines) {
            String personName = null;
            Date personBirthdate = null;
            Pattern personNamePattern = Pattern.compile("\\D+");
            Matcher personNameMatcher = personNamePattern.matcher(line);

            if (personNameMatcher.find()) {
                personName = personNameMatcher.group().trim();
            }

            Pattern personBirthdatePattern = Pattern.compile("\\d{1,2}\\s\\d{1,2}\\s\\d{4}");
            Matcher personBirthdateMatcher = personBirthdatePattern.matcher(line);

            if (personBirthdateMatcher.find()) {
                SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
                try {
                    String dateString = personBirthdateMatcher.group();
                    personBirthdate = format.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (personName !=null && personBirthdate != null) {
                Solution.PEOPLE.add(new Person(personName, personBirthdate));
            }
        }
    }
}
