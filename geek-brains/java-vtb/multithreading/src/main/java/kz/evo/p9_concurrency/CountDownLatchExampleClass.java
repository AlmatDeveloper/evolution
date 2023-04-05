package kz.evo.p9_concurrency;

import java.util.concurrent.CountDownLatch;

// если у нас много потоков по программе и у нас нет возможности получить на них ссылки
// и main поток должен дождаться остановки всех потоков(join прописать не получится так как нет ссылок)
// в этом случае поможет защелка
public class CountDownLatchExampleClass {
    public static void main(String[] args) {
        final int THREADS_COUNT = 6;

        // создаем защелку и говорим сколько раз она должна щелкнуть
        // нужен для того чтобы один поток подождал другие потоки с которыми у него нет связи
        // countDownLatch одноразовый, это означает что при обнулении, не будет возможности вернуться на значение параметра
        // как вариант его можно пересоздать
        final CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

        System.out.println("Начинаем");

        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
               try {
                   System.out.println("start sleep");
                   Thread.sleep(200 * w + (int)(500 * Math.random()));
                   System.out.println("end sleep");
                   // щелкаем
                   // продвинутый вариант join
                   System.out.println("Поток #" + w + " - готов");
                   countDownLatch.countDown();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }).start();
        }

        try {
            // Переводит main поток в состояние ожидания пока защелка не дойдет до 0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // увидим в последнею очередь так как main поток ждет остальные потоки
        System.out.println("Работа завершена");
    }
}
