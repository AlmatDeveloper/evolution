package kz.evo.p1_race_condition;

public class Counter {
    private int c;

    public Counter() {
        this.c = 0;
    }

    public int getC() {
        return c;
    }

    public void inc() {
        this.c++;
    }

    public void dec() {
        this.c--;
    }
}
