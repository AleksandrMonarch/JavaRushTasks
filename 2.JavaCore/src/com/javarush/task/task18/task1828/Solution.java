package com.javarush.task.task18.task1828;                                                                                                                                                                                                                                                                                                                                                              
                                                                                                                                                                                                                                                                                                                                                              
import java.io.*;                                                                                                                                                                                                                                                                                                                                                              
import java.util.ArrayList;
import java.util.List;
                                                                                                                                                                                                                                                                                                                                                              
/*                                                                                                                                                                                                                                                                                                                                                               
Прайсы 2                                                                                                                                                                                                                                                                                                                                                              
*/                                                                                                                                                                                                                                                                                                                                                              
                                                                                                                                                                                                                                                                                                                                                              
public class Solution {                                                                                                                                                                                                                                                                                                                                                              
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = consoleReader.readLine();


    }

    public static void updateFile(String filePath, String id, String productName, String price, String quantity) throws IOException {

        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath));
        List<String> fileLines = new ArrayList<>();

        String result = String.format("%-8s%-30s%-8s%-4s", id, productName, price, quantity);

        while (fileReader.ready()) {
            fileLines.add(fileReader.readLine());
        }

        for (String s : fileLines) {
            if (s.startsWith(id, 8)) {
            }
        }
    }
}