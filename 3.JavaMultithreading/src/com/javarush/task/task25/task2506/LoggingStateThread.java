package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {

    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State currentState = thread.getState();
        System.out.println(currentState);
        while (currentState != State.TERMINATED) {
            if (currentState != thread.getState()) {
                currentState = thread.getState();
                System.out.println(currentState);
            }
        }
    }
}
