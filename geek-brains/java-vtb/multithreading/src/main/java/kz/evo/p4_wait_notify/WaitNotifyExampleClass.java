package kz.evo.p4_wait_notify;

// в момент вызова wait() поток освобождает монитор
// у каждого потока свой stacktrace, вызовы методов каждый поток хранит независимо от других потоков
// потоки не могут смотреть у друг-друга стек, у каждого свой
// heap общий (но разделенный для каждого потока)
public class WaitNotifyExampleClass {
    // если lock дать другое значение, то потоки отработают параллельно, не дожидаясь друг-друга
    // так же будет и с Integer (final переменные)
    private final String lock = "monitor";

    // отключаем кэширование в ядре процессора
    private volatile char letter = 'A';
    // здесь можно убрать volatile
    // так как мы синхронизируемся по одному объекту
    // java сама позаботиться чтобы потоки читали одну и туже переменную

    public static void main(String[] args) {
        var waitNotifyExampleClass = new WaitNotifyExampleClass();

        new Thread(waitNotifyExampleClass::printA).start();
        new Thread(waitNotifyExampleClass::printB).start();
        new Thread(waitNotifyExampleClass::printC).start();
        new Thread(waitNotifyExampleClass::printD).start();
    }

    public void printA() {
        block: synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                // while нужен для того чтобы, проверять при каждом пробуждении букву
                // если будет if, то он отработает один раз и сломает очередь
                while (letter != 'A') {
                    System.out.println("wait A");
                    try {
                        // при следующем пробуждении поток стартанет с места вызова wait
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("A");
                letter = 'B';
                lock.notifyAll();
            }
        }
    }

    public void printB() {
        block: synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                while (letter != 'B') {
                    System.out.println("wait B");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("B");
                letter = 'C';
                lock.notifyAll();
            }
        }
    }

    public void printC() {
        block: synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                while (letter != 'C') {
                    System.out.println("wait C");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("C");
                letter = 'D';
                lock.notifyAll();
            }
        }
    }

    public void printD() {
        block: synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                while (letter != 'D') {
                    System.out.println("wait D");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("D");
                letter = 'A';
                lock.notifyAll();
            }
        }
    }
}