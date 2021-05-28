package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class University {

    private String name;
    private int age;

    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        students.stream().filter(student -> student.getAverageGrade() >= averageGrade).findFirst().get();
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        return students.stream().max(Comparator.comparingDouble(Student::getAverageGrade)).get();
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        return students.stream().min(Comparator.comparingDouble(Student::getAverageGrade)).get();
    }

    public void expel(Student student) {
        students.remove(student);
    }
}