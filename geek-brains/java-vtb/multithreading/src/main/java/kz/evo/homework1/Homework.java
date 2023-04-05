package kz.evo.homework1;

import java.util.Arrays;

public class Homework {
//    static double[] array = new double[100_000_000];
    static double[] array1 = new double[50_000_000];
    static double[] array2 = new double[50_000_000];

    public static void main(String[] args) throws InterruptedException {
//        Arrays.fill(array, 1);

        Arrays.fill(array1, 1);
        Arrays.fill(array2, 1);

        long start = System.currentTimeMillis();
        System.out.println("go");
//
//        for (int i = 0; i < array.length; i++) {
//            array[i] = formula(array[i]);
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
        Thread t1 = new Thread(() -> {
            System.out.println("go1");
            for (int i = 0; i < array1.length; i++) {
                array1[i] = formula(array1[i]);
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("go2");
            for (int i = 0; i < array2.length; i++) {
                array2[i] = formula(array2[i]);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
    }

    public static double formula(double f) {
        return f * Math.sin(0.2 + f / 5) * Math.cos(0.2 + f / 5)  * Math.cos(0.4 + f / 5);
    }
}
