package kz.evo.p1_race_condition;

// ключевое слово synchronized означает что в любой синхронизированный метод данного класса
// в единицу времени может обращаться только один поток
// то есть может выполняться только один синхронизированный метод в единицу времени
public class SyncCounter {
    private int c;

    public SyncCounter() {
        this.c = 0;
    }

    public int getC() {
        return c;
    }

    // если какой - то поток вызвал данный метод
    // то остальные потоки не смогут обратиться к другим синхронизированным методам
    synchronized public void inc() {
        this.c++;
    }

    synchronized public void dec() {
        this.c--;
    }
}
