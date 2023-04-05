package kz.evo.p2_synchronization;

public class SyncMonitorExampleClass {
    final Object monitor = new Object();

    public static void main(String[] args) {
        var syncMonitorExampleClass = new SyncMonitorExampleClass();
        new Thread(syncMonitorExampleClass::method).start();
        new Thread(syncMonitorExampleClass::method).start();
        new Thread(syncMonitorExampleClass::method).start();
    }

    // если хотим чтобы часть метода выполнялось параллельно, другая часть последовательно
    public void method() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " имитация работы параллельно");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // использования synchronized в блоке кода
        // Object используется для общего монитора
        // когда все потоки дойдут до данной части когда, окажется что только один может выполнить этот блок
        // поток который будет выполнять данную часть кода захватит монитор,
        // остальные потоки будут ждать пока монитор освободится
        block: synchronized (monitor) {
            // если поток который попал в этот блок
            // но при этом код в блоке синхронизации уже выполняется другим потоком,
            // то поток который попал в этот блок уже не сможет выйти и будет ждать завершения другого потока
            // то есть он будет простаивать, хотя мог бы делать что то полезное
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " имитация работы по очереди");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
