package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
//            File yourFile = File.createTempFile("your_file_name", null);
            String yourFile = "C:\\Users\\Aleksandr\\Desktop\\originalFile.txt";
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User savedUser = new User();
            savedUser.setFirstName("Aleksandr");
            savedUser.setLastName("Tsarev");
            savedUser.setBirthDate(new Date());
            savedUser.setMale(true);
            savedUser.setCountry(User.Country.RUSSIA);
            javaRush.users.add(savedUser);
            javaRush.save(outputStream);
            outputStream.flush();
            outputStream.close();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            User loadedUser = loadedObject.users.get(0);
            String loaderUserInfo = loadedUser.getFirstName() + " " +
                    loadedUser.getLastName() + " " +
                    loadedUser.getBirthDate() + " " +
                    loadedUser.isMale() + " " +
                    loadedUser.getCountry();
            System.out.println(loaderUserInfo);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

//            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println(!users.isEmpty() ? "no empty" : "empty");
            if (!users.isEmpty()) {
                for (User user : users) {
                    String line = user.getFirstName() + " " +
                            user.getLastName() + " " +
                            user.getBirthDate().getTime() + " " +
                            user.isMale() + " " +
                            user.getCountry();
                    printStream.println(line);
                }
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if ("no empty".equals(reader.readLine())) {
                while (reader.ready()) {
                    String[] userParams = reader.readLine().split("\\s");
                    User user = new User();
                    user.setFirstName(userParams[0]);
                    user.setLastName(userParams[1]);
                    user.setBirthDate(new Date(Long.parseLong(userParams[2])));
                    user.setMale(Boolean.parseBoolean(userParams[3]));
                    user.setCountry(User.Country.valueOf(userParams[4]));
                    users.add(user);
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
