package kz.evo.chapter7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chapter7Test {

    // синтаксис java позволяет присваивать в ряд несколько переменных
    @Test
    void initVars() {
        int a, b, c, d;
        a = b = c = d = 10;

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(c, d);
        assertEquals(d, 10);
    }
}
