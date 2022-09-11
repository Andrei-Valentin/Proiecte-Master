import java.util.ArrayDeque;

public class PCArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> sharedQueue = new ArrayDeque<>();
        Object lock = new Object();
        Thread prod = new Thread(new Producer(sharedQueue, lock));
        Thread cons = new Thread(new Consumer(sharedQueue, lock));
        prod.start();
        cons.start();
    }
}

class Producer implements Runnable {
    private ArrayDeque<Integer> sharedQueue;
    private Object lock;

    public Producer(ArrayDeque<Integer> sharedQueue, Object lock) {
        this.sharedQueue = sharedQueue;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 1_000_000; i++) {
                sharedQueue.addLast(i);
            }
        }
    }
}

class Consumer implements Runnable {
    private ArrayDeque<Integer> sharedQueue;
    private Object lock;

    public Consumer(ArrayDeque<Integer> sharedQueue, Object lock) {
        this.sharedQueue = sharedQueue;
        this.lock = lock;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1_000_000; i++) {
            synchronized (lock) {
                int num = sharedQueue.pollFirst();
            }
        }
    }
}