import java.util.concurrent.*;

public class PCBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();

        Thread prodThread1 = new Thread(new Producer(sharedQueue, 1));
        Thread prodThread2 = new Thread(new Producer(sharedQueue, 2));
        

        Thread consThread1 = new Thread(new Consumer(sharedQueue, 1));
        Thread consThread2 = new Thread(new Consumer(sharedQueue, 2));
        Thread consThread3 = new Thread(new Consumer(sharedQueue, 3));

        prodThread1.start();
        prodThread2.start();
        consThread1.start();
        consThread2.start();
        consThread3.start();
    }    
}

class Producer implements Runnable {
    private BlockingQueue<Integer> sharedQueue;
    private int threadNo;

    public Producer(BlockingQueue<Integer> sharedQueue, int threadNo) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println("Produced: " + i + " by thread " + threadNo);
                sharedQueue.put(i);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> sharedQueue;
    private int threadNo;

    public Consumer(BlockingQueue<Integer> sharedQueue, int threadNo) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int num = sharedQueue.take();
                System.out.println("Consumed: " + num + " by thread " + threadNo);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

