package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {

    private String fileName;
    transient private FileOutputStream stream;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) {

        try (FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            Solution outSolution = new Solution("C:\\Users\\Aleksandr\\Desktop\\updatedFile.txt");
            outSolution.writeObject("AAA");
            objectOutputStream.writeObject(outSolution);
            System.out.println(outSolution.stream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Aleksandr\\Desktop\\originalFile.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Solution inSolution = (Solution) objectInputStream.readObject();
            System.out.println(inSolution.stream.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
