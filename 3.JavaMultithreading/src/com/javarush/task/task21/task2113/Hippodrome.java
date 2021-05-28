package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    public static Hippodrome game;

    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        for (int i = 1; i < horses.size(); i++) {
            winner = horses.get(i - 1).getDistance() > horses.get(i).getDistance() ? horses.get(i - 1) : horses.get(i);
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }


    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Sam", 3.0d, 0.0d));
        horses.add(new Horse("Flash", 3.0d, 0.0d));
        horses.add(new Horse("Speedy", 3.0d, 0.0d));

        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
