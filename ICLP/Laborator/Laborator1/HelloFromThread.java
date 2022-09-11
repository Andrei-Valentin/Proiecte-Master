public class HelloFromThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from Thread!");
    }
    public static void main(String[] args) {
        HelloFromThread t = new HelloFromThread();
        t.start();
        System.out.println("Hello from main!");
    }
}
