package kz.evo.chapter7;

public class InitStage {
    static int i = 0;

    static {
        System.out.println("static block");
        i = 1;
    }

    {
        System.out.println("ordinary block");
        i = 2;
    }

    public InitStage() {
        System.out.println("constructor");
        i = 3;
    }

    public static void main(String[] args) {
        var initStage = new InitStage();
    }
}