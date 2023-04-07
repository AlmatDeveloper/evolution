package kz.evo.p6_exceptions;

// так как у каждого потока свой stackTrace
// то ошибки невозможно перехватить в main потоке
public class ThreadExceptions {
    public static void main(String[] args) {
        correctCatchExceptionInThread();
//        incorrectCatchExceptionInThread();
    }

    public static void correctCatchExceptionInThread() {
        var thread = new Thread(() -> {
            int x = 0;
            System.out.println(1);
            x = x / 0;
            System.out.println(2);
        });

        // чтобы отлавливать ошибки в потоках в main потоке
        // если попытаемся обработать в main потоке, то не получиться отловить ошибку
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(e.getMessage());
            System.out.println("catch");
        });

        thread.start();

        // так же есть глобальный стандартный перехватчик исключений
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//
//            }
//        });
    }

    public static void incorrectCatchExceptionInThread() {
        try {
            var thread = new Thread(() -> {
                int x = 0;
                System.out.println(1);
                x = x / 0;
                System.out.println(2);
            });

            thread.start();
        } catch (ArithmeticException e) {
            System.out.println("catch incorrect");
        }
    }
}