package kz.evo.p3_deadlock;

// может быть такая ситуация при которой потоки могут заблокироваться(deadlock)
// из-за того что первый поток захочет переключиться на второй монитор
// но второй монитор заблокирован вторым потоком,
// который(второй поток) хочет переключиться на первый монитор
// как решение: нужно переключаться по мониторам в правильном порядке
public class DeadlockRepairedClassExample {
    public static void main(String[] args) {
        final String monitor1 = "monitor1";
        final String monitor2 = "monitor2";

        var thread1 = new Thread(() -> {
            block: synchronized (monitor1) {
                System.out.println("первый поток в первом мониторе");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("первый поток пытается переключиться на второй монитор");
                synchronized (monitor2) {
                    System.out.println("это не сработает");
                }
            }
        });

        var thread2 = new Thread(() -> {
            block: synchronized (monitor2) {
                System.out.println("второй поток во втором мониторе");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("второй поток пытается переключиться на первый монитор");
                synchronized (monitor1) {
                    System.out.println("это не сработает");
                }
            }
        });

        thread1.start();
        thread2.start();

//        while (true) {
//            try {
//                Thread.sleep(2_000);
//                System.out.println("thread 1" + thread1.isAlive());
//                System.out.println("thread 1" + thread1.isInterrupted());
//
//                System.out.println("thread 2" + thread2.isAlive());
//                System.out.println("thread 2" + thread2.isInterrupted());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}