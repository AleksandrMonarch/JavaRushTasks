package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {

//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt".getBytes());
//        System.setIn(byteArrayInputStream);

        ArrayList<String> fileWords = new ArrayList<>();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(consoleReader.readLine()))) {

            while (fileReader.ready()) {
                fileWords.addAll(Arrays.asList(fileReader.readLine().split("\\s")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < fileWords.size(); i++) {
            String noReverseWord = fileWords.get(i);
            for (int j = i + 1; j < fileWords.size(); j++) {
                String maybeReverseWord = fileWords.get(j);
                if (noReverseWord.equals(new StringBuilder(maybeReverseWord).reverse().toString())) {
                    Pair pairToResult = new Pair();
                    pairToResult.first = noReverseWord;
                    pairToResult.second = maybeReverseWord;
                    result.add(pairToResult);
                    fileWords.remove(j);
                    break;
                }
            }
        }
        System.out.println(result);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }
}
