package kz.evo.chapter6;

class Stack {
    final int[] array;
    private int top; // верхушка стека
    private final int capacity;

    public Stack(int size) {
        array = new int[size];
        top = -1;
        capacity = size;
    }

    // поместить
    public void push(int item) {
        if (top == (capacity - 1)) {
            System.out.println("Стек переполнен");
            System.exit(-1);
        }

        array[++top] = item;
    }

    // извлечь
    public int pop() {
        if(top < 0) {
            System.out.println("Стек пуст");
            System.exit(-1);
        } else {
            return array[top--];
        }

        return -1;
    }
}
