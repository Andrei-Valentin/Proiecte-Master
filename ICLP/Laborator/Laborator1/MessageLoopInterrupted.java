public class MessageLoopInterrupted {
    public static void main(String[] args) throws InterruptedException {
        long patience = 1000 * 30;
        long startTime = System.currentTimeMillis();

        MessageLoop.threadMessage("Starting MessageLoop thread");

        Thread t = new Thread(new MessageLoop());
        t.start();

        while (t.isAlive()) {
            MessageLoop.threadMessage("Still waiting...");
            t.join(2000);

            if ((System.currentTimeMillis() - startTime > patience) && t.isAlive()) {
                MessageLoop.threadMessage("Tired of waiting");
                t.interrupt();
                t.join();
            }
        }
        MessageLoop.threadMessage("Finally");
    }
}

class MessageLoop implements Runnable {
    public void run() {
        String messages[] = {"This", "is", "very", "important"};
        try {
            for (int i = 0; i < messages.length; ++i) {
                Thread.sleep(4000);
                threadMessage(messages[i]);
            }
        }
        catch (InterruptedException ex) {
            threadMessage("I wasn't done!");
        }
    }

    public static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + message);
    }
}