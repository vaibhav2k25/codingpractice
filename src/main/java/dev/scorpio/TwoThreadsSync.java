package dev.scorpio;

public class TwoThreadsSync {

    private boolean boolA = true;

    public synchronized void printA() throws InterruptedException {
        while (true) {
            while (!boolA)
                wait();
            System.out.println("a");
            Thread.sleep(1000);
            boolA = false;
            notify();
        }
    }

    public synchronized void printB() throws InterruptedException {
        while (true) {
            while (boolA)
                wait();
            System.out.println("b");
            Thread.sleep(1000);
            boolA = true;
            notify();
        }
    }

    public static void main(String[] args) {
        TwoThreadsSync obj = new TwoThreadsSync();
        Thread t1 = new Thread(() -> {
            try {
                obj.printA();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                obj.printB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }

}
