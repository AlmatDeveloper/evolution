package kz.evo.p2_synchronization.double_counter;

// стоит задуматься
// если хотим чтобы потоки меняли две разные переменные,
// но при этом у нас все методы помечены как synchronized
// то это приведет к тому что независящие друг от друга потоки будут находиться в ожидании
public class NonCorrectSyncDoubleCounter {
    private long c1 = 0;
    private long c2 = 0;

    public synchronized void incC1() {
        ++c1;
    }

    public synchronized void decC1() {
        --c1;
    }

    public synchronized void incC2() {
        ++c2;
    }

    public synchronized void decC2() {
        --c2;
    }

    public long getC1() {
        return c1;
    }

    public long getC2() {
        return c2;
    }
}
