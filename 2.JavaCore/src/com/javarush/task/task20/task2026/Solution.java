package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

import java.util.Arrays;

// TODO: 27.12.2020 write rectangle solution
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int rectangleCounter = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1) {
                    rectangleCounter++;
                    detectRectangle(i, j, a);
                }
            }
        }
        return rectangleCounter;
    }

    public static void detectRectangle(int indexI, int indexJ, byte[][] a) {
        for (int i = indexI; i < a.length; i++) {
            if (a[i][indexJ] == 0) break;
            a[i][indexJ] = 0;
            for (int j = indexJ + 1; j < a[i].length; j++) {
                if (a[i][j] == 0) break;
                a[i][j] = 0;
            }
        }
    }
}
