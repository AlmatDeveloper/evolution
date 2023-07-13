package kz.evo.p9_concurrency;

import java.util.concurrent.Semaphore;

// если у нас есть критическая секция куда нужно пустить несколько потоков,
// блок synchronized не подойдет
// в этом случае можем использовать semaphore
public class SimpleSemaphoreExampleClass {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(4);

        Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                int time = 3 + (int) (Math.random() + 4.0);
                int num = count++;

                try {
                    // перед началом выполнения какой-то операции поток захватывает semaphore
                    // тогда количество доступов(permits) уменьшается на 1
                    // как только количество будет равно 0 то следующий поток будет ожидать
                    semaphore.acquire();
                    System.out.println("Поток №" + num + " начинает выполняться");
                    Thread.sleep(time * 1_000L);
                    System.out.println("Поток №" + num + " завершил работу");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // как только поток делает release то счетчик доступов увеличится на 1
                    // и следующий поток проходит в этот блок кода
                    semaphore.release();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}