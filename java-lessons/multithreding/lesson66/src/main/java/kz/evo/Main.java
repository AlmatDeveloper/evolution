package kz.evo;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
//        myThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("this is new thread");
        m();
    }

    public void m() {
        System.out.println("dsgf");
//        throw new RuntimeException();
    }
}