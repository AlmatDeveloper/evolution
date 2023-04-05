package kz.evo.p7_locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// readWriteLock
// нужно придерживаться правила:
// читать один ресурс могут сколько угодно потоков(и никто не может менять этот ресурс пока остальные читают)
// менять ресурс за единицу времени может только один поток
// потоки стают в очередь, для работы с ресурсом
// например были два потока на чтение, потом присоединился потока на запись
// после двух потоков на чтение отработает поток на запись
public class RRWLockExampleClass {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        // увидим параллельное чтение
        // ни одна запись не будет параллельно другой записи или другому параллельному чтению
        // то есть только один поток может записывать (другие потоки записи и чтения будут ожидать)
        new Thread(() -> {
            lock.readLock().lock();

            try {
                System.out.println("READING start 1 thread");
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("READING end 1 thread");
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            lock.readLock().lock();

            try {
                System.out.println("READING start 2 thread");
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("READING end 2 thread");
                lock.readLock().unlock();
            }
        }).start();

        new Thread(() -> {
            lock.writeLock().lock();

            try {
                System.out.println("WRITING start 3 thread");
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("WRITING end 3 thread");
                lock.writeLock().unlock();
            }
        }).start();

        new Thread(() -> {
            lock.writeLock().lock();

            try {
                System.out.println("WRITING start 4 thread");
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("WRITING end 4 thread");
                lock.writeLock().unlock();
            }
        }).start();
    }
}