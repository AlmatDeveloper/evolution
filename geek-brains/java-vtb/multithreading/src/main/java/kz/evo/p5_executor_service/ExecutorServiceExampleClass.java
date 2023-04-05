package kz.evo.p5_executor_service;

import java.util.concurrent.*;

// executorService основные виды
// 1 - single пул из одного потока
// 2 - fixed создает фиксированное количество потоков
// 3 - cached создает потоки по мере поступления задач
public class ExecutorServiceExampleClass {
    public static void main(String[] args) throws InterruptedException {
//        demonstration();
        tasksQueue();
    }

    public static void demonstration() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            final int j = i;
            executorService.execute(() -> {
                System.out.println(j + " - Begin " + Thread.currentThread().getName());

                try {
                    Thread.sleep(100 + (int) (3000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(j + " - End");
            });
        }

        // нужно обязательно останавливать
        // переходят в состояние termination
        executorService.shutdown();
        executorService.close();

        // Для всех потоков вызывается interrupt
        // если логика interrupt прописана, то это сработает
        // если нет, то отработает как обычный shutdown
        executorService.shutdownNow();

        // подобие join для пула потоков
        executorService.awaitTermination(50, TimeUnit.DAYS);

        // получить ответ из потока
        // типа return - а
        // так как execute void
        // Future - информация о выполнении задачи
        // можно передать callable, который будет возвращать какое-то значение
        // можно передать runnable, который будет говорить завершена ли задача или нет
        var stringFuture = executorService.submit(() -> 456);

        // даже если пул будет обсчитывать задачу некоторое время
        // метод get будет ожидать результата
        // почти как join
        try {
            stringFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // можно передать список задач и получить список ответов
//        executorService.invokeAll();
    }

    // ExecutorService создает потоки по порядку
    public static void tasksQueue() throws InterruptedException {
        // threadFactory нужен для подготовки создания потоков
        // когда мы создаем потоки при помощи наследования от Thread
        // у нас есть возможность настроить его (дать название, сделать его демоном, приоритет)
        // в случае threadFactory то же самое
        // Runnable просто нужен для выполнения нашей задачи, именно туда вписывается логика
        ExecutorService executorService = Executors.newFixedThreadPool(3, r -> {
            var thread = new Thread(r);
//            thread.setPriority(10);
//            thread.setName("ABC");
            System.out.println("Thread created");
            return thread;
        });

        // по мере поступления задач указанное количество потоков
        // если задач окажется больше, то просто будут использоваться потоки, которые создались на предыдущих задачах
        Thread.sleep(2000);
        executorService.execute(() -> System.out.println(20));
        Thread.sleep(2000);
        executorService.execute(() -> System.out.println(30));
        Thread.sleep(2000);
        executorService.execute(() -> System.out.println(40));
        Thread.sleep(2000);
        executorService.execute(() -> System.out.println(50));
        Thread.sleep(2000);
        executorService.execute(() -> System.out.println(60));
        Thread.sleep(2000);

        executorService.shutdown();
    }
}