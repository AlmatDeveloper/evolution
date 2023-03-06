package kz.evo;

// выведите числа от 1 до 10 рекурсивно
public class chapter2_ex1 {
    public static void main(String[] args) {
        fromTo(1, 11);
    }

    public static void fromTo(int from, int to) {
        if (from == to) {
            return;
        }
        System.out.println(from);
        fromTo(from + 1, to);
    }
}