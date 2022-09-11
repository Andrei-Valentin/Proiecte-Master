import java.util.concurrent.Semaphore;

// implementam modelul de concurenta Reader-Writer
// astfel incat threadurile Reader sa aiba prioritate fata de threadurile Writer 

// ReadCnt global 
// doua semafoare - mutex, wrt 

// avem  mai multe threaduri Reader si Writer 
// se acceseaza o zona critica
// pot fi mai multe threaduri Reader in paralel 
// poate fi un singur Writer 

public class ReaderWriter {
    public static void main(String[] args) {
        Semaphore wrt = new Semaphore(1);
        Semaphore mutex = new Semaphore(1);

        (new Thread(new Writer(wrt))).start();
        (new Thread(new Reader(wrt, mutex))).start();
        (new Thread(new Reader(wrt, mutex))).start();
        (new Thread(new Reader(wrt, mutex))).start();
        (new Thread(new Writer(wrt))).start();
        (new Thread(new Writer(wrt))).start();
        (new Thread(new Reader(wrt, mutex))).start();
    }
}

class Writer implements Runnable {
    private Semaphore wrt; 

    public Writer(Semaphore wrt) {
        this.wrt = wrt; 
    }

    @Override
    public void run() {
        try {
            // Writer cere sa scrie in zona critica
            wrt.acquire();
            // Write face o operatie in zona critica
            System.out.println("Writer " + Thread.currentThread().getName() + " scrie in zona critica");
            Thread.sleep(200);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            // Writer paraseste zona critica 
            wrt.release();
        }
    }
}

class Reader implements Runnable {
    private Semaphore wrt;
    private Semaphore mutex; 

    public Reader(Semaphore wrt, Semaphore mutex) {
        this.wrt = wrt;
        this.mutex = mutex; 
    }

    @Override
    public void run() {
        try {
            // Read vrea sa citeasca din zona critica
            mutex.acquire();
            // Daca a reusit sa intre in zona critica, incrementeaza numarul cititorilor in paralel 
            ReadCounter.inc();
            // trebuie sa asiguram faptul ca Readerii au prioritate 
            // daca este deja un Reader care acceseaza resursa, nu lasam Writerul sa fure zona de memorie 
            if (ReadCounter.get() == 1) {
                wrt.acquire();
            }
            // permitem altor Readeri sa intre
            mutex.release(); 

            // facem o operatie de citire in zona critica
            System.out.println("Readerul " + Thread.currentThread().getName() + " citeste din zona critica");
            Thread.sleep(100);

            // ne pregatim sa parasim zona critica 
            mutex.acquire();
            // decrementam numarul de Readeri care citesc in paralel 
            ReadCounter.dec();
            // si daca nu mai sunt Readeri care acceseaza resursa, permitem threadurilor Writer sa o acceseze 
            if (ReadCounter.get() == 0) {
                wrt.release();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            mutex.release();
        }
    }
}

class ReadCounter { 
    private static int readcnt; 

    public static int get() {
        return readcnt;
    }

    public static void inc() {
        readcnt++;
    }

    public static void dec() {
        readcnt--;
    }
}