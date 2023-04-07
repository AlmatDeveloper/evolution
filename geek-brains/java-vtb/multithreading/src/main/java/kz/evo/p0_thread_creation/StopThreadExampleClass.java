package kz.evo.p0_thread_creation;

// поток можно принудительно остановить
public class StopThreadExampleClass {
    public static void main(String[] args) {
//        incorrectVersion();
        correctVersion();
    }

    public static void incorrectVersion() {
        var thread = new Thread(() -> {
            while (true) {
                System.out.println("bad tick");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // метод deprecated, может повредить данные если остановим поток принудительно
        thread.stop();
    }

    public static void correctVersion() {
        Thread thread = new Thread(() -> {
            // переменная нужна для отлова ошибки
            // если interrupt не сработает
            // тем самым появляется возможность корректно остановить поток
            boolean inter = false;
            while (true) {
                System.out.println(Thread.currentThread().isInterrupted());
                // interrupt нужно обрабатывать самим для остановки потока
                if (Thread.currentThread().isInterrupted() || inter) {
                    break;
                }

                System.out.println("good tick");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // ошибка случается тогда,
                    // когда пытаемся вызвать поток,
                    // который не может сейчас ответить
                    inter = true;
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("try interrupt");
        thread.interrupt();
    }
}