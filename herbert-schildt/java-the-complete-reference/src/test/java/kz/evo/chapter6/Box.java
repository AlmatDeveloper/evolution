package kz.evo.chapter6;

public class Box {
    private double wight;
    private double height;
    private double depth;

    public static void main(String[] args) {
        Box box = new Box();
        box.depth = 100;
        box.wight = 100;
        box.height = 100;

        var vol = box.depth * box.height * box.wight;
        System.out.println("Объем: " + vol);
    }
}