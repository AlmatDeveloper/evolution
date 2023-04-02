package kz.evo.chapter7;

public class Overload {
    // для перегрузки метода необходимы разные аргументы(типы и/или количество)
    public static void main(String[] args) {
        byte b = 88;
        new Overload().overloadMethod(b);

        int i = 88;
        new Overload().overloadMethod(i);

        double d = 88;
        new Overload().overloadMethod(d);

        long l = 88;
        new Overload().overloadMethod(l);

        String s = "88";
        int i2 = 88;
        new Overload().overloadMethod(s, i2);
    }

    public void overloadMethod() {
    }

    public void overloadMethod(byte i) {
    }

    public void overloadMethod(int i) {
    }

    public void overloadMethod(double i) {
    }

    public void overloadMethod(long l) {
    }

    public int overloadMethod(String s, int i) {
        return 0;
    }
}
