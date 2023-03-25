package kz.evo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Chapter4Test {
    @Test
    public void arithmeticOperation() {
        System.out.println("Целочисленная арифметика");
        int a = 1 + 1;
        int b = a * 3;
        int c = b / 4;
        int d = c - a;
        int e = -d;

        assertEquals(a, 2);
        assertEquals(b, 6);
        assertEquals(c, 1);
        assertEquals(d, -1);
        assertEquals(e, 1);

        // Арифметические операции со значениями double
        System.out.println ("Арифметика с плавающей точкой") ;
        double da = 1 + 1;
        double db = da * 3;
        double dc = db / 4;
        double dd = dc - a;
        double de = -dd;

        assertEquals(da, 2.0);
        assertEquals(db, 6.0);
        assertEquals(dc, 1.5);
        assertEquals(dd, -0.5);
        assertEquals(de, 0.5);

        int x = 42;
        double y= 42.25;

        assertEquals(x % 10, 2);
        assertEquals(y % 10, 2.25);
    }

    @Test
    public void incrementDecrement() {
        int x = 5;
        int y = ++x;

        assertEquals(y, 6);

        int x1 = 5;
        int y1 = x1++;

        assertEquals(y1, 5);
    }
}
