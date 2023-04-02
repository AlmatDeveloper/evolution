package kz.evo;

// поток можно принудительно остановить
public class StopThreadExampleClass {
    public static void main(String[] args) {
//        badVersion();
        goodVersion();
    }

    public static void badVersion() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("bad tick");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // метод deprecated, может повредить данные если остановим поток принудительно
        thread.stop();
    }

    public static void goodVersion() {
        Thread thread = new Thread(() -> {
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
                    // ошибка случается тогда, когда пытаемся вызвать поток, который не может сейчас ответить
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

        for (int i = 0; i < 50; i++) {
            // если вызвать interrupt у потока, но в этот момент он не доступен(может во сне), то ничего не произойдет
            thread.interrupt();
        }
    }
}
