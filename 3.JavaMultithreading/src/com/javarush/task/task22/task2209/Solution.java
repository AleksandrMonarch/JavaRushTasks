package com.javarush.task.task22.task2209;

/* 
Составить цепочку слов
*/

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {

//        ByteArrayInputStream fullFileName = new ByteArrayInputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt".getBytes());
//        System.setIn(fullFileName);

        StringBuilder fileLines = new StringBuilder();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(consoleReader.readLine()))) {
            while (fileReader.ready()) {
                fileLines.append(fileReader.readLine()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] words = fileLines.toString().trim().split("\\s");

        //...
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {

        if (words == null || words.length == 0) return new StringBuilder();

        return new StringBuilder(new WordGraph(words).getWordChain());
    }

    public static class WordGraph {

        ArrayList<WordVertex> wordVertices = new ArrayList<>();
        int[][] adjacencyMatrix;
        LinkedList<Integer> queue = new LinkedList<>();

        public WordGraph(String[] words) {

            for (String word : words) {
                wordVertices.add(new WordVertex(word));
            }

            adjacencyMatrix = new int[words.length][words.length];

            for (int i = 0; i < wordVertices.size(); i++) {
                String word = wordVertices.get(i).word;
                for (int j = i + 1; j < wordVertices.size(); j++) {
                    String otherWord = wordVertices.get(j).word;

                    if (word.toLowerCase().charAt(word.length() - 1) == otherWord.toLowerCase().charAt(0)) {
                        adjacencyMatrix[i][j] = 1;
                    }
                    if (otherWord.toLowerCase().charAt(otherWord.length() - 1) == word.toLowerCase().charAt(0)) {
                        adjacencyMatrix[j][i] = 1;
                    }
                }
            }
        }

        public String getWordChain() {

            StringBuilder wordChain = new StringBuilder();

            for (int i = 0; i < wordVertices.size(); i++) {
                wordVertices.get(i).isVisited = true;
                queue.add(i);

                while (queue.size() != 0) {
                    if (queue.size() == wordVertices.size()) {
                        for (Integer vertexIndex : queue) {
                            wordChain.append(wordVertices.get(vertexIndex).word).append(" ");
                        }
                        return wordChain.toString().trim();
                    }

                    int adjacencyIndex = getAdjacencyVertex(queue.getLast());

                    if (adjacencyIndex != -1) {
                        wordVertices.get(adjacencyIndex).isVisited = true;
                        queue.add(adjacencyIndex);
                    } else {
                        queue.removeLast();
                    }
                }

                for (WordVertex vertex : wordVertices) {
                    vertex.isVisited = false;
                }
            }
            return wordChain.toString();
        }

        public int getAdjacencyVertex(int adjacencyIndex) {
            for (int j = 0; j < adjacencyMatrix[adjacencyIndex].length; j++) {
                if (adjacencyMatrix[adjacencyIndex][j] != 0 && !wordVertices.get(j).isVisited) {
                    return j;
                }
            }
            return -1;
        }
    }

    public static class WordVertex {

        String word;
        boolean isVisited;

        public WordVertex(String word) {
            this.word = word;
        }
    }
}
