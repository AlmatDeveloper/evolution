package kz.evo.chapter3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Chapter3Test {

    @Test
    public void charIsPositiveNumber() {
        char c = 'a';
        char c1 = 97;

        int i = c + c1;

        assertEquals(c, c1);
        assertEquals(i, 194);
    }

    @Test
    public void numbersCanBeSnakeCase() {
        int i = 123_456_789;
        int i1 = 123__456__789;
        int i2 = 123456789;

        double d = 123.456_789;
        double d1 = 1_23.456__789;
        double d2 = 123.456789;

        assertEquals(i, i1);
        assertEquals(i1, i2);

        assertEquals(d, d1);
        assertEquals(d1, d2);
    }

    @Test
    public void floatNumberDefaultTypeIsDouble() {
        double d = 789.0;
        float f = 789.0f; // явно указываем что это float

        assertEquals(f, d);
    }

    @Test
    public void blocks() {
        int i = 1;
        {
            {
                int i1 = 1;
                assertEquals(i, i1); // блоки определяют область видимости и жизненный цикл переменной
            }
        }
    }

    @Test
    public void fromNumberToCharNeedExplicitConversion() {
        byte b = 21;
        short s = 21;
        int i = 21;
        // нужно явное преобразование из числа в символ (даже если char больше byte)
        char c = (char) b;
        char c1 = (char) s;
        char c2 = (char) i;

        assertEquals(c, c1, c2);
    }

    @Test
    public void castingIncompatibleTypes() {
        byte bi;
        byte bd;
        int i = 257;
        double d = 323.142;

        System.out.println("\nПреобразование int в byte.");
        bi = (byte) i;
        System.out.println("i и bi: " + i + " " + bi);

        System.out.println("\nПреобразование double в int . ");
        i = (int) d;
        System.out.println("d и i: " + d + " " + i);

        System.out.println("\nПреобразование double в byte.");
        bd = (byte) d;
        System.out.println("d и bd: " + d + " " + bd);

        assertNotEquals(bi, bd);
    }

    // автоматическое повышение типов в выражениях
    // byte, short или char автоматически повышаются до int
    @Test
    public void automaticTypePromotionInExpressions() {
        byte a = 40;
        byte b = 50;
        byte c = 100;
        int i = a * b / c; // повышается до int
        byte b1 = (byte) (a * b / c);

        long l = Long.MAX_VALUE - 1;
        float f = l - 5f; // повышается до float, так как в выражении есть float
        double d = l - f; // можем повысить до double

        assertEquals(i, b1);
    }

    @Test
    public void arrayDeclaration() {
        int[] array = new int[10];
        int array1[] = new int[10];

        int[][] array2 = new int[10][];
        array2[0] = new int[10];
        array2[1] = new int[11];
        array2[2] = new int[12];
        int array3[][] = new int[10][];
        array3[0] = new int[10];
        array3[1] = new int[11];
        array3[2] = new int[12];

        int[] array4 = {1, 2, 3};
        int array5[] = {1, 2, 3};

        int[][] array6 = {{1, 2, 3}, {1, 2, 3}};
        int array7[][] = {{1, 2, 3}, {1, 2, 3}};

        assertArrayEquals(array, array1);
        assertArrayEquals(array2, array3);
        assertArrayEquals(array4, array5);
        assertArrayEquals(array6, array7);
    }

}
