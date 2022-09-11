public class HelloFromThreadRunnable implements Runnable {
    @Override
    public void run() {
       System.out.println("Hello from Thread!");
    }
    public static void main(String[] args) {
        Thread t = new Thread(new HelloFromThreadRunnable());
        t.start();
        System.out.println("Hello from main!");
    }
}
