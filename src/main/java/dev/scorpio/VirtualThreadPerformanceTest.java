package dev.scorpio;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadPerformanceTest {

    static final int TASK_COUNT = 10_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Running with platform threads...");
        runWithPlatformThreads();

        System.out.println("\nRunning with virtual threads...");
        runWithVirtualThreads();
    }

    static void runWithPlatformThreads() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        Instant start = Instant.now();

        CountDownLatch latch = new CountDownLatch(TASK_COUNT);

        for (int i = 0; i < TASK_COUNT; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(100);  // Simulate blocking work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        Duration time = Duration.between(start, Instant.now());
        System.out.println("Platform Threads: Completed in " + time.toMillis() + " ms");
    }

    static void runWithVirtualThreads() throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Instant start = Instant.now();

        CountDownLatch latch = new CountDownLatch(TASK_COUNT);

        for (int i = 0; i < TASK_COUNT; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(100);  // Simulate blocking work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        Duration time = Duration.between(start, Instant.now());
        System.out.println("Virtual Threads: Completed in " + time.toMillis() + " ms");
    }
}
