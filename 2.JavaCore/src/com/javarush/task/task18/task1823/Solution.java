package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (consoleReader.ready()) {
                String line = consoleReader.readLine();
                if ("exit".equals(line)) break;
                Thread thread = new ReadThread(line);
                thread.start();
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ReadThread extends Thread {
        public ReadThread(String fileName) {
            //implement constructor body
            super(fileName);
        }
        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            Map<Byte, Integer> bytesCounters = new HashMap<>();
            try (FileInputStream fis = new FileInputStream(this.getName())){
                while (fis.available() > 0) {
                    byte fileByte = (byte) fis.read();
                    if (!bytesCounters.containsKey(fileByte)) {
                        bytesCounters.put(fileByte, 1);
                    } else {
                        bytesCounters.put(fileByte, bytesCounters.get(fileByte) + 1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte targetByte = 0;
            int maxCount = 0;
            for (Map.Entry<Byte, Integer> pair : bytesCounters.entrySet()) {
                if (pair.getValue() > maxCount) {
                    maxCount = pair.getValue();
                    targetByte = pair.getKey();
                }
            }
            synchronized (resultMap) {
                resultMap.put(this.getName(), (int) targetByte);
            }
        }
    }
}
