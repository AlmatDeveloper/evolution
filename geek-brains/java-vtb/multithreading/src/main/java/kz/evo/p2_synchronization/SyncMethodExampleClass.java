package kz.evo.p2_synchronization;

// как поток понимает что можно ли ему вызвать синхронизированный метод или нет
// для этого есть монитор(может быть занят, может быть свободен)
// его может кто-то занять и освободить
public class SyncMethodExampleClass {
    public static void main(String[] args) {
        // монитор1
        var SyncMethodExampleClass1 = new SyncMethodExampleClass();
        // монитор1
        var SyncMethodExampleClass2 = new SyncMethodExampleClass();

        // когда данный поток заходит в метод, то монитор захватывается
        new Thread(SyncMethodExampleClass1::method1).start();
        // второй поток заходит в метод и смотрит занят ли его монитор
        new Thread(SyncMethodExampleClass1::method2).start();
        // два метода отработают последовательно
        // если вызвать один и тот же синхронизированный метод, они так же исполнятся последовательно

        new Thread(SyncMethodExampleClass1::method3).start();

        // два разных монитора никак не мешают друг-другу и выполняются параллельно(даже если методы помечены synchronized)
        new Thread(SyncMethodExampleClass2::method1).start();
        new Thread(SyncMethodExampleClass2::method2).start();

    }

    // когда поток заходит в блок синхронизации, он смотрит на монитор
    // в роли монитора может выступать любой объект
    // если мы вызываем данный метод, то для него монитором служит объект который его вызвал
    public synchronized void method1() {
        System.out.println("METHOD1-START");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("METHOD1-END");
    }

    public synchronized void method2() {
        System.out.println("METHOD2-START");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("METHOD2-END");
    }

    // при вызове не синхронизированного метода поток не смотрит на его монитор и выполняет его параллельно
    public void method3() {
        System.out.println("METHOD3-START");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("METHOD3-END");
    }
}
