package kz.evo.p2_synchronization.double_counter;

// есть три вида синхронизации
// 1 - по методу, 2 - по статическому методу, 3 - по монитору(объект)
public class DemonstrationChairsExampleClass {
    public static void main(String[] args) {
        // первые два человека будут поочередно садиться на первый стул
        // так как имеют общий монитор
        var стул1 = new Object();
        // третий человек садится на второй стул независимо ни от кого
        // так как у него свой монитор который занят только им самим
        var стул2 = new Object();

        // человек1
        new Thread(() -> {
            System.out.println("Человек1 подошел к стулу1");

            block1: synchronized (стул1) {
                System.out.println("Человек1 сел на стул1");
                try {
                    Thread.sleep(1_00);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Человек1 встал со стула1");
            }
        }).start();

        // человек2
        new Thread(() -> {
            System.out.println("Человек2 подошел к стулу1");

            block2: synchronized (стул1) {
                System.out.println("Человек2 сел на стул1");
                try {
                    Thread.sleep(1_50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Человек2 встал со стула1");
            }
        }).start();

        // человек3
        new Thread(() -> {
            System.out.println("Человек3 подошел к стулу2");

            block2: synchronized (стул2) {
                System.out.println("Человек3 сел на стул2");
                try {
                    Thread.sleep(1_50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Человек3 встал со стула2");
            }
        }).start();
    }
}