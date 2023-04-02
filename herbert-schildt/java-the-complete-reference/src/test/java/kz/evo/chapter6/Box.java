package kz.evo.chapter6;

public class Box {
    double width;
    double height;
    double depth;

    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public Box() {
    }

    // Отобразить объем коробки
    double volume() {
        return width * height * depth;
    }
}