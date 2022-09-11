// Semaforul este o valoare intreaga, care poate fi incrementata / decrementata, dar nu poate fi negativa 
// Semaphore - acquire si release 
// pentru a obtine numele unui Thread, putem utiliza Thread.currentThread().getName()
//                                                                         .getId()
import java.util.concurrent.*;

public class Semaphores {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
        (new Thread(new MyThread(semaphore))).start();
    }
}

class MyThread implements Runnable {
    private Semaphore semaphore;

    public MyThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " is performing operation " + i);
                Thread.sleep(1000);
            }
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            semaphore.release();
       }
    }
}