package kz.evo.p0_thread_creation;

// за создание потоков отвечает класс Thread
// Thread может быть не удобен если класс уже наследуется от другого класса
// Thread можно наследовать если хотим его как то подкрутить
// вместо Thread можно использовать Runnable
public class ThreadExampleClass extends Thread {

    // описываем что должен делать поток в методе run
    // в этом примере просто вывод текста с перерывом по 1с
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new ThreadExampleClass();

        // для запуска служит метод start(если вызвать метод run то он выполнится в основном потоке)
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
