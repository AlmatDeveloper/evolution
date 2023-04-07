package kz.evo.p2_synchronization.double_counter;

// пример использования synchronized в блоке
// создаем разные мониторы для каждой независимой друг от друга переменной
// чтобы потоки не блокировались при выполнении из-за synchronized в сигнатуре
public class CorrectSyncDoubleCounter {
    private long c1 = 0;
    private long c2 = 0;

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public long getC1() {
        return c1;
    }

    public long getC2() {
        return c2;
    }

    public void incC1() {
        block: synchronized (lock1) {
            ++c1;
        }
    }

    public void decC1() {
        block: synchronized (lock1) {
            --c1;
        }
    }

    public void incC2() {
        block: synchronized (lock2) {
            ++c2;
        }
    }

    public void decC2() {
        block: synchronized (lock2) {
            --c2;
        }
    }
}