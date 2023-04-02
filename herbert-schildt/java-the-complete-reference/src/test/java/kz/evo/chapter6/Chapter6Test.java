package kz.evo.chapter6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Chapter6Test {

    @Test
    public void initVarOfObj() {
        var box = new Box();
        box.depth = 1;
        box.width = 2;
        box.height = 3;

        System.out.println("Объем: " + box.volume());

        assertEquals(box.depth, 1);
        assertEquals(box.width, 2);
        assertEquals(box.height, 3);
    }

    // два объекта будут ссылаться на одну и туже область памяти
    // изменения одного будут влиять на другой объект
    @Test
    public void sameLink() {
        var box1 = new Box();
        var box2 = box1;

        box2.depth = 3;
        box2.height = 4;
        box2.width = 5;

        assertEquals(box1, box2);
        assertEquals(box1.height, box2.height);
        assertEquals(box1.width, box2.width);
        assertEquals(box1.depth, box2.depth);
        assertEquals(box1.volume(), box2.volume());

        // если указать на null, то объект просто отсоединяется(не перетирает объект в null)
    }
}