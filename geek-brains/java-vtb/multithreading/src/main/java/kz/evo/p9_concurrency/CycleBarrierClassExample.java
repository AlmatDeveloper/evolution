package kz.evo.p9_concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycleBarrierClassExample {
    public static void main(String[] args) {
        // за-синхронизировать между собой этапы какого-то количество потоков
        // как пример - скачки лошадей, когда лошади подходят к какому-то барьеру
        // и при поднятии барьера лошади начинают скакать
        // cyclicBarrier цикличен, это означает что при обнулении счетчика, он обратно вернется к своему параметру
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        final int THREAD_COUNT = 10;

        for (int i = 0; i < THREAD_COUNT; i++) {
            int w = i;
            new Thread(() -> {
                System.out.println("Подготавливаемся " + w);
                try {
                    Thread.sleep(2_500 + 500 * (int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Готов " + w);

                try {
                    // когда потоки готовы, они останавливаются на await
                    // при await счетчик понижается на единицу
                    // при достижении 0 потоки выполняют код дальше
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                // нужно чтобы поехали все вместе
                System.out.println("Поехал " + w);
            }).start();
        }
    }
}