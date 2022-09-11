import java.util.concurrent.*;

// un singur producator 
// 5 consumatori 
// iar zona partajata de memorie, accesata de consumatori, sa fie arbitrata prin intermediul unui semafor 

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> drop = new LinkedBlockingQueue<>();
        Semaphore semaphore = new Semaphore(1);

        (new Thread(new Producer(drop))).start();

        for (int i = 0; i < 5; ++i) {
            (new Thread(new Consumer(drop, semaphore))).start();
        }
    }
}

class Producer implements Runnable {
    private BlockingQueue<String> sharedQueue;

    public Producer(BlockingQueue<String> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        String messages[] = new String[200];
        for (int i = 0; i < 200; i++) {
            messages[i] = "m" + i;
        }

        for (int i = 0; i < messages.length; i++) {
            try {
                System.out.println("Produced " + messages[i] + " by thread " + Thread.currentThread().getName());
                sharedQueue.put(messages[i]);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<String> sharedQueue;
    private Semaphore semaphore;

    public Consumer(BlockingQueue<String> sharedQueue, Semaphore semaphore) {
        this.sharedQueue = sharedQueue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphore.acquire();
                String value = sharedQueue.take();
                System.out.println("Consumer " + Thread.currentThread().getName() + " consumed " + value);
                // Thread.sleep(1000);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                semaphore.release();
            }
        }
    }
}