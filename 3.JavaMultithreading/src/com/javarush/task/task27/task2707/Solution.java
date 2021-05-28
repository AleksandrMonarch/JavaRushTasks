package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/

public class Solution {

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
//                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        synchronized (o1) {

            Thread thread1 = new Thread(()-> {
                solution.someMethodWithSynchronizedBlocks(o1, o2);
            });

            Thread thread2 = new Thread(()-> {
                synchronized (o2) {
                }
            });

            thread1.setDaemon(true);
            thread1.start();
            thread2.start();
            Thread.sleep(10);
            return (thread1.getState() == Thread.State.BLOCKED && thread2.getState() != Thread.State.BLOCKED);
        }
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }

    public static boolean o1LockedFirst(Solution solution, Object o1, Object o2) throws Exception {
        int maxIterations = 10000;
        int trueCounter = 0;
        int falseCounter = 0;
        for (int i = 0; i < maxIterations; i++) {
            if (isLockOrderNormal(solution, o1, o2)) {
                trueCounter++;
            } else {
                falseCounter++;
            }
        }

        System.out.println("true: " + trueCounter);
        System.out.println("false " + falseCounter);
        return (trueCounter == maxIterations && falseCounter == 0);
    }

    public static boolean o2LockedFirst(Solution solution, Object o1, Object o2) throws Exception {
        int maxIterations = 10000;
        int trueCounter = 0;
        int falseCounter = 0;
        for (int i = 0; i < maxIterations; i++) {
            if (!isLockOrderNormal(solution, o1, o2)) {
                falseCounter++;
            } else {
                trueCounter++;
            }
        }
        System.out.println("true" + trueCounter);
        System.out.println("false" + falseCounter);
        return (trueCounter == 0 && falseCounter == maxIterations);
    }
}
