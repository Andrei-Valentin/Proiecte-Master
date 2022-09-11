public class HelloFromThreadAn {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Hello from Thread!");
            }
            catch (InterruptedException ex) {
                System.out.println(ex);
            }
        });
        t.start();

        System.out.println("Hello from main!");
    }
}
