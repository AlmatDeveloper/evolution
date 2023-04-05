package kz.evo.p1_race_condition;

// пример использования синхронизации
public class SyncExampleClass {
    public static void main(String[] args) {
        var counter = new SyncCounter();

        var thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.inc();

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.dec();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // нет гарантии того что потоки запустятся один за другим
        thread.start();
        thread1.start();

        try {
            // работают последовательно
            // main поток будет ждать вначале первый join, потом второй
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // будем получать 0, так как есть синхронизация
        System.out.println(counter.getC());
    }
}