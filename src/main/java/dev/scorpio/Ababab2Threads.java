package dev.scorpio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Ababab2Threads {
    boolean isA = true;
    int max = 10;
    int count = 0;

    public static void main(String[] args) {
        System.out.println("abab using 2 threads.");
        Ababab2Threads m = new Ababab2Threads();
        Thread t1 = new Thread(()-> {
            try {
                m.printA();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()-> {
            try {
                m.printB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }

    private synchronized void printA() throws InterruptedException {
        while (count < max) {
            while (!isA) {
                wait();
            }
            System.out.print("a");
            isA = false;
            count++;
            notifyAll();
        }
    }

    private synchronized void printB() throws InterruptedException {
        while (count < max) {
            while (isA) {
                wait();
            }
            System.out.print("b");
            if(count<max)
                System.out.print("-");
            isA = true;
//            count++;
            notifyAll();
        }
    }
}
