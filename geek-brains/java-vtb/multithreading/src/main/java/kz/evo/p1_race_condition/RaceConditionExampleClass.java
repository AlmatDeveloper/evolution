package kz.evo.p1_race_condition;

// data condition частный случай race condition (соревнуются за данные)
// race condition - состояние гонки(соревнуются за процессорное время)
// случается когда несколько потоков работают с одной переменной
// пример: если два потока одновременно считали значение, изменили и хотят вернуть его,
// то в переменную запишется значение того потока который отработал последним
// чтобы такой ситуации не случалось, потоки нужно синхронизировать
public class RaceConditionExampleClass {
    public static void main(String[] args) {
        var counter = new Counter();

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

        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // не всегда будем получать 0
        // хоть и 0 + 100 - 100 = 0
        System.out.println(counter.getC());
    }
}