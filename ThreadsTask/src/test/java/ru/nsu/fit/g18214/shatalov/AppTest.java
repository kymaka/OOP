/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.g18214.shatalov;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class AppTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ThreadFind threadFind = new ThreadFind();
        long end, start;
        Integer[] arr = new Integer[8000];
        Arrays.fill(arr, 1000000007);
        Iterator<Integer> iterator = Arrays.stream(arr).iterator();
        for (int j = 1; j <= 3; j++) {
            System.out.println("Run " + j + ":");
            for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
                iterator = Arrays.stream(arr).iterator();
                start = System.currentTimeMillis();
                threadFind.findThreadPool(iterator, i);
                end = System.currentTimeMillis();
                System.out.println("Time for " + i + " thread(s): " + ((double) end - start) / 10000);
            }
        }
    }

    @Test
    public void testSingle() throws ExecutionException, InterruptedException {
        ThreadFind threadFind = new ThreadFind();
        long end, start;
        Integer[] arr = new Integer[8000];
        Arrays.fill(arr, 1000000007);
        Iterator<Integer> iterator = Arrays.stream(arr).iterator();
        for (int j = 1; j <= 3; j++) {
            System.out.println("Run " + j + ":");
            iterator = Arrays.stream(arr).iterator();
            start = System.currentTimeMillis();
            threadFind.findThreadPool(iterator, 1);
            end = System.currentTimeMillis();
            System.out.println("Time for " + 1 + " thread(s): " + ((double) end - start) / 10000);
        }
    }

    @Test
    public void testParallel() throws ExecutionException, InterruptedException {
        ThreadFind threadFind = new ThreadFind();
        long end, start;
        Integer[] arr = new Integer[8000];
        Arrays.fill(arr, 1000000007);
        Iterator<Integer> iterator = Arrays.stream(arr).iterator();
        for (int j = 1; j <= 3; j++) {
            System.out.println("Run " + j + ":");
            for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
                iterator = Arrays.stream(arr).iterator();
                start = System.currentTimeMillis();
                threadFind.findStream(iterator, i);
                end = System.currentTimeMillis();
                System.out.println("Time for " + i + " thread(s): " + ((double) end - start) / 10000);
            }
        }
    }
}
