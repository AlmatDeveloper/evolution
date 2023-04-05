package kz.evo.chapter7;

public class Final {
    private final static int i = 0;
    private final static int i1;

    private final int i2 = 2;
    private final int i3;
    private final int i4;

    static {
        i1 = 1;
    }

    {
        i3 = 3;
    }

    public Final() {
        i4 = 4;
    }

    // final над аргументом означает что переменная не будет модифицироваться внутри метода
    public static void main(final String[] args) {
        System.out.println(Final.i);
        System.out.println(Final.i1);

        Final f = new Final();

        System.out.println(f.i2);
        System.out.println(f.i3);
        System.out.println(f.i4);

        localFinal: {
            // final над локальной переменной дает возможность присвоить ей значение только один раз
            final int i;
            i = 0;
        }
    }
}
