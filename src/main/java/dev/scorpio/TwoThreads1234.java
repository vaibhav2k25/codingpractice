package dev.scorpio;

public class TwoThreads1234 {

    int max = 10;
    boolean isEven = false;
    private volatile int counter = 1;

    public static void main(String[] args) {
        System.out.println("Using 2 threads to write 1234");
        TwoThreads1234 obj = new TwoThreads1234();
        Thread t1 = new Thread(() -> {
            try {
                obj.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                obj.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }

    private synchronized void printOdd() throws InterruptedException {
        while (counter <= max) {
            while (isEven)
                wait();

            System.out.println(counter);
            counter++;
            notifyAll();
        }
    }

    private synchronized void printEven() throws InterruptedException {
        while (counter <= max) {
            while (!isEven)
                wait();

            System.out.println(counter);
            counter++;
            notifyAll();
        }
    }
}
