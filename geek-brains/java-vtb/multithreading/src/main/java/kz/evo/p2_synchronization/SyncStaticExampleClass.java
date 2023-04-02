package kz.evo.p2_synchronization;

// если synchronized повешан на статический метод, то монитором будет служить сам класс в котором есть этот метод
// классы так же подгружаются как и объекты в память, из-за этого класс можно использовать как объект
public class SyncStaticExampleClass {
    public static void main(String[] args) {
        // если запустить статический и нестатический синхронизированные методы, они будут выполняться параллельно
        // так как у них разные мониторы(у одного класс, у другого объект)

        //static
        new Thread(SyncStaticExampleClass::classMethod).start();
        new Thread(SyncStaticExampleClass::classMethod).start();

        var syncStaticExampleClass = new SyncStaticExampleClass();
        //non static
//        new Thread(syncStaticExampleClass::objectMethod).start();
//        new Thread(syncStaticExampleClass::objectMethod).start();
    }

    // если поток вызвал данный метод, никакой другой поток не может вызывать статический синхронизированный метод в классе
    public synchronized static void classMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println("static method sync " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void objectMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println("non static method sync " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
