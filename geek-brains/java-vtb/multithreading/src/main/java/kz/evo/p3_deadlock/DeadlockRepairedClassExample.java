package kz.evo.p3_deadlock;

// может быть такая ситуация при которой потоки могут заблокироваться(deadlock)
// из за того что первый поток захочет переключиться на второй монитор
// но второй монитор заблокирован вторым потоком, который(второй поток) же хочет переключиться на первый монитор
// как решение: нужно переключаться по мониторам в правильном порядке
public class DeadlockRepairedClassExample {
    public static void main(String[] args) {
        String monitor1 = "monitor1";
        String monitor2 = "monitor2";

        new Thread(() -> {
            block: synchronized (monitor1) {
                System.out.println("первый поток в первом мониторе");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("первый поток пытается переключиться на второй монитор");
                block1: synchronized (monitor2) {
                    System.out.println("это не сработает");
                }
            }
        }).start();

        new Thread(() -> {
            block: synchronized (monitor2) {
                System.out.println("второй поток во втором мониторе");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("второй поток пытается переключиться на первый монитор");
                block1: synchronized (monitor1) {
                    System.out.println("это не сработает");
                }
            }
        }).start();
    }
}
