package kz.evo.p0_thread_creation;

// Runnable - интерфейс в котором хранится бизнес логика потока
public class RunnableExampleClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("runnable - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new RunnableExampleClass()).start();

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("main - " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}