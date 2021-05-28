package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) {
        System.out.println(new Solution(4));
        Solution savedObject = new Solution(4);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt"))) {
            outputStream.writeObject(savedObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Solution loadedObject = new Solution(10);

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt"))) {
            loadedObject = (Solution) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(savedObject.toString().equals(loadedObject.toString()));
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
