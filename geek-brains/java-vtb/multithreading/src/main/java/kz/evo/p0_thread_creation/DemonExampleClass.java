package kz.evo.p0_thread_creation;

// java приложение работает пока есть хотя бы один поток
// потоки могут быть обычными, а так же демонами
// в качестве примера - демон поток можно использовать когда нам не жалко остановить данный поток
public class DemonExampleClass {
    public static void main(String[] args) {
        var thread = new Thread(() -> {
            var time = 0;
            for (int i = 0; i < 4; i++) {
                try {
                    Thread.sleep(500);
                    ++time;
                    System.out.println("time - " + time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // потоки демоны работают до тех пор пока работает хотя бы один поток в приложении
        thread.setDaemon(true);
        // получить статус потока(не имеет смысла отталкиваться от статуса так как он может быстро поменяться)
        thread.getState();
        // активен ли поток
        thread.isAlive();
        // чем выше приоритет тем чаще ОС будет переключаться на этот поток
        thread.setPriority(5);
        // при попытке второй раз вызвать метод старт после остановки потока - получим ошибку
        thread.start();

        System.out.println("main -> sleep");

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main -> end");
    }
}
