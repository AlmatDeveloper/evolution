package kz.evo.p6_exceptions;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

// в случае executorService и callable мы можем отработать ошибку,
// случившуюся в потоке в main потоке
// callable прокидывает ошибку дальше (может в main поток)
// и можем обработать ее в основном потоке
public class ThreadPoolExceptions {
    public static void main(String[] args) {
        catchCallable();
    }

    public static void catchCallable() {
        var executorService = Executors.newFixedThreadPool(2);

        var submit = executorService.submit(() -> {
            int x = 10 / 0;
            return "word";
        });

        try {
            submit.get();
        } catch (InterruptedException e) {
            System.out.println("interrupt " + e.getMessage());
        } catch (ExecutionException e) {
            System.out.println("execution " + e.getMessage());
        }

        executorService.shutdown();
    }
}