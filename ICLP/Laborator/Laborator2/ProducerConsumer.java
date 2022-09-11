import java.util.Random;

public class ProducerConsumer {
    public static void main(String[] args) {
        PCDrop drop = new PCDrop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }    
}

class Producer implements Runnable {
    private PCDrop drop;

    public Producer(PCDrop drop) {
        this.drop = drop; 
    }

    @Override
    public void run() {
        String messages[] = { "this", "is", "an", "important", "message"};
        Random random = new Random();

        for (int i = 0; i < messages.length; i++) {
            System.out.format("Message: %s\n", messages[i]);
            drop.put(messages[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }   
        }

        drop.put("DONE");
    }
}

class Consumer implements Runnable {
    private PCDrop drop;

    public Consumer(PCDrop drop) {
        this.drop = drop; 
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            System.out.format("Message received: %s\n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class PCDrop {
    private String message; 
    private boolean isEmpty = true; 

    public synchronized String take() {
        while (isEmpty) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        isEmpty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        while (!isEmpty) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        isEmpty = false;
        this.message = message;
        notifyAll();
    }
}

