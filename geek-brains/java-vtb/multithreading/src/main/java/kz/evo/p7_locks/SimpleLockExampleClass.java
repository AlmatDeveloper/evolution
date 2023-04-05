package kz.evo.p7_locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// lock - замки почти то же самое что и синхронизация по монитору
// такой же блок синхронизации как и в случае с монитором
// в отличии от блоков синхронизации lock и unlock может вызывать в разных методах
// так же у Lock есть дополнительные методы помимо lock
public class SimpleLockExampleClass {
    public static void main(String[] args) {
        // final локально означает что присвоение значения к переменной будет лишь один раз
        // что то типа монитора по которому блоки кода будут синхронизироваться
        final Lock lock = new ReentrantLock();

        // какой-то из потоков дойдет до lock
        // и выполнит код после лока, другие потоки будут ожидать его
        // далее поток отпускает лок и остальные потоки будут выполнять свой код после кода с локом
        new Thread(() -> {
            // выполниться параллельно
            System.out.println("THREAD BEFORE LOCK 1");
//            try {
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            lock.lock();
            // выполниться последовательно
            System.out.println("THREAD GET LOCK 1");

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("THREAD END 1");
                lock.unlock();
            }
        }).start();

//        new Thread(() -> {
//            // выполниться параллельно
//            System.out.println("THREAD BEFORE LOCK 2");
//            lock.lock();
//            // выполниться последовательно
//            System.out.println("THREAD GET LOCK 2");
//
//            try {
//                Thread.sleep(8000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println("THREAD END 2");
//                lock.unlock();
//            }
//        }).start();

        // tryLock возвращает boolean значение
        // поток попытается захватить блок кода, если он занят то пойдет дальше
        // так же есть возможность указать время, за которое будет проверяться tryLock
        new Thread(() -> {
            System.out.println("THREAD BEFORE LOCK 3");

            try {
                // третий поток ждет 4 секунды, если tryLock возвращает false то поток идет дальше
//                if (lock.tryLock(4L, TimeUnit.SECONDS)) {
                if (lock.tryLock(10L, TimeUnit.SECONDS)) {
                    System.out.println("THREAD GET LOCK 3");
                    try {
                        Thread.sleep(6_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // желательно всегда использовать finally
                        lock.unlock();
                        System.out.println("THREAD END 3");
                    }
                } else {
                    System.out.println("Не очень-то и хотелось ...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
