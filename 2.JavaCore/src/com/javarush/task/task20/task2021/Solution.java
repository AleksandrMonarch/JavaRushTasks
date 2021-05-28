package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/

public class Solution implements Serializable {

    public static class SubSolution extends Solution {

        private void writeObject(ObjectOutputStream out) throws IOException {
            throw new NotSerializableException();
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SubSolution subSolution = new SubSolution();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(subSolution);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        SubSolution subSolution1 = (SubSolution) objectInputStream.readObject();


    }
}
