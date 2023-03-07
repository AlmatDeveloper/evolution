package kz.evo;

// выведите числа от 1 до 10 рекурсивно
public class chapter2_ex1 {
    public static void main(String[] args) {
        recursiveOutput(1, 11);
    }

    public static void recursiveOutput(int from, int to) {
        if (from == to) {
            return;
        }
        System.out.println(from);
        recursiveOutput(from + 1, to);
    }
}