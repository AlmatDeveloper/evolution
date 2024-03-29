package kz.evo;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chapter5Test {

    // в любом операторе можно опустить часть с блоком(кроме switch)
    // оператор for состоит из 3 частей:
    // 1 - инициализация, 2 - условие, 3 - итерация(каждый из них может работать независимо друг от друга)
    @Test
    public void loopWithoutBody() {
        int i, j;
        i = 100;
        j = 200;

        while (++i < --j) ;

        System.out.printf("i = %d; j = %d\n", i, j);

        assertEquals(i, j);

        int k = 0;
        for (; k < 50; k++) ;

        System.out.printf("k = %d\n", k);

        assertEquals(k, 50);
    }

    @Test
    public void systemInRead() throws IOException {
        System.out.println(System.in.read());
    }

    // цикл for - инициализация, условие, итерация
    @Test
    public void forLoop() {
        for (int i = 0, j = 0; i < 5 && j == 0; i++) {
            System.out.println(i);
            System.out.println(j);
            i++;
        }

        boolean flag = true;
        for (; flag; ) {
            System.out.println("flag");
            flag = false;
        }

    }

    // foreach служит только для чтения
    // менять значения в массиве не предусмотрено
    @Test
    public void foreachNotChangeArray() {
        int[] ints = new int[]{3, 45, 5, 2, 3, 4, 54};
        for (int i = 0; i < ints.length; i++) {
            ints[i] = ints[i] * 10;
        }

        // никак не влияет на основной массив, foreach нужен только для чтения
        for (var i : ints) {
            i = i * 10;
        }

        assertArrayEquals(ints, new int[]{30, 450, 50, 20, 30, 40, 540});
    }

    // к любому оператору можно дать маркер
    @Test
    public void breakWithMarker() {
        boolean b = true;

        marker1:
        {
            System.out.println("marker 1 start");
            marker2:
            {
                System.out.println("marker 2 start");
                marker3:
                {
                    System.out.println("marker 3 start");
                    if (b) break marker3;
                    System.out.println("marker 3 end");
                }
                System.out.println("marker 2 end");
            }
            System.out.println("marker 1 end");
        }

        // как пример выйти из обоих циклов
//        ifMarker: if (true) {
        outer:
        for (int i = 0; i < 4; i++) {
            System.out.print("Проход " + i + ": ");
            for (int j = 0; j < 11; j++) {
                ifBlock: if (j * i == 20) break outer; // выйти из обоих циклов
//                    if (j * i == 20) break ifMarker; // выйти из обоих циклов
                System.out.print(j + " ");
            }
//            System.out.println("Этo выводиться не будет.");
        }
        System.out.println("Циклы завершены.");
//        }
    }
}
