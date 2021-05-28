package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

// TODO: 01.01.2021 write crossword solution 

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'e', 'e', 'e', 'l', 'e'},
                {'u', 's', 'n', 'n', 'n', 'o'},
                {'l', 'e', 'n', 'o', 'n', 'e'},
                {'m', 'm', 'n', 'n', 'n', 'h'},
                {'p', 'e', 'e', 'e', 'j', 'e'},
        };

        List<Word> words = (detectAllWords(crossword, "one"));
        for (Word word : words) {
            System.out.println(word);
        }
        System.out.println(words.size());
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        for (String word : words) {
            Word resultWord = new Word(word);
            detectWord(crossword, word, resultWord);
            result.add(resultWord);
        }
        return result;
    }

    private static void detectWord(int[][] crossword, String word, Word resultWord) {
        for (int x = 0; x < crossword.length; x++) {
            for (int y = 0; y < crossword[x].length; y++) {
                if (crossword[x][y] == word.charAt(0)) {
                    if (getCourse(crossword, word, resultWord, x, y)) break;
                }
            }
        }
    }

    private static boolean getCourse(int[][] crossword, String word, Word resultWord, int startX, int startY) {
        for (int courseX = -1; courseX <= 1; courseX++) {
            for (int courseY = -1; courseY <= 1; courseY++) {
                if (keepCourse(crossword, word, resultWord, startX, startY, courseX, courseY)) return true;
            }
        }
        return false;
    }

    private static boolean keepCourse(int[][] crossword, String word, Word resultWord, int startX, int startY, int courseX, int courseY) {
        int endX = startX;
        int endY = startY;

        for (int charIndex = 1; charIndex < word.length(); charIndex++) {
            endX += courseX;
            endY += courseY;
            if (endX < 0 || endX >= crossword.length) return false;
            if (endY < 0 || endY >= crossword[endX].length) return false;
            if (word.charAt(charIndex) != crossword[endX][endY]) return false;
        }
        resultWord.setStartPoint(startY, startX);
        resultWord.setEndPoint(endY, endX);
        return true;
    }


    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
