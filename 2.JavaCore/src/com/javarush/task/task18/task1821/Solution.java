package com.javarush.task.task18.task1821;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        if (args.length != 0) {
            String filePath = args[0];
            FileInputStream fileInputStream = new FileInputStream(filePath);
            TreeMap<AsciChar, Integer> resultMap = new TreeMap<>();
            byte[] fileBytes = new byte[fileInputStream.available()];
            fileInputStream.read(fileBytes);
            for (byte fileByte : fileBytes) {
                char byteChar = (char) fileByte;
            }
        }
    }
    private static class AsciChar implements Comparable {
        private byte byteCode;
        private char symbol;

        public AsciChar(byte byteCode, char symbol) {
            this.byteCode = byteCode;
            this.symbol = symbol;
        }

        public byte getByteCode() {
            return byteCode;
        }

        @Override
        public int compareTo(Object o) {
            if (this == o) return 0;
            if (o instanceof AsciChar) {
                AsciChar asciChar = (AsciChar) o;
                if (this.byteCode == ((AsciChar) o).byteCode) return 0;
                if ((this.byteCode > asciChar.byteCode)) return 1;
            }
            return -1;
        }
    }
}