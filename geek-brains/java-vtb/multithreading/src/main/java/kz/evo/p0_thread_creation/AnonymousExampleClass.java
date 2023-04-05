package kz.evo.p0_thread_creation;

// если логика потока проста, то можно использовать анонимные внутренние классы
public class AnonymousExampleClass {
    public static void main(String[] args) {
        System.out.println("main start");

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("runnable - " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            // если хотим чтобы main дождался окончания данного потока, то используем join
            // так же есть ожидание по времени
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("main - " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("END"); // end напечатается после выполнения потока
    }
}
