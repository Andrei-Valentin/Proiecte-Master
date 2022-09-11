// in modelul Reader Writer avem o resursa partajata 
// avem doua roluri - Writer si Reader 
// pot fi mai multi cititori simultan 
// dar se poate face o singura scriere simultan 
// in momentul in care se face o scriere, nu se permite o citire din resursa respectiva 

// o solutie pentru problema in care Reader are prioritate peste Writer 

// rolul Writer: 
// - Writer cere sa scrie in zona critica;
// - Writer scrie / face o operatie in zona critica;
// - Writer paraseste zona critica. 

// rolul Reader 
// - Reader cere sa citeasca din zona critica; 
// - daca a reusit sa intre in respectiva zona, incrementeaza nr de readeri care acceseaza acum resursa;
// - daca este deja un Reader, nu vom permite Writer-ului sa fure zona de memorie;
// - permitem si altor Readeri sa intre;
// - facem o citire din zona respectiva; 
// - readerul paraseste zona critica. 
//      - decrementam numarul de readeri activi;
//      - daca am ajuns la 0 readeri activi, inseamna ca putem permite unui Writer sa scrie. 

// vom utiliza doua semafoare 
// wrt - este utilizat comun atat de Reader, cat si de Writer 
// mutex - este utilizat doar de Reader 


import java.util.concurrent.*;

public class ReaderWriter {
    public static void main(String[] args) {
        Semaphore wrt = new Semaphore(1);
        Semaphore mutex = new Semaphore(1);

        (new Thread(new Writer(wrt))).start();
        (new Thread(new Writer(wrt))).start();
        (new Thread(new Reader(wrt, mutex))).start();
        (new Thread(new Reader(wrt, mutex))).start();
        (new Thread(new Writer(wrt))).start();
        (new Thread(new Writer(wrt))).start();
        (new Thread(new Reader(wrt, mutex))).start();
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
        //while (true) {
            try {
                // Writer cere sa scrie in zona critica
                wrt.acquire();
                // Write face o operatie in zona critica 
                System.out.println("Writer " + Thread.currentThread().getName() + " scrie");
                Thread.sleep(1000);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                // Writer paraseste zona critica
                wrt.release();
            }
        //}
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
        //while (true) { 
            try {
                // Reader vrea sa citeasca din zona critica 
                mutex.acquire();
                
                // daca a reusit sa intre, incrementeaza numarul cititorilor in paralel 
                ReadCounter.inc();

                // asiguram faptul ca readerii au prioritate
                // daca este deja un reader, nu lasam Writerul sa fure zona de memorie 
                if (ReadCounter.get() == 1) {
                    wrt.acquire();
                }

                // permitem altori Readeri sa intre 
                mutex.release();

                // aici fac efectiv citirea din zona critica si alte operatii cerute 
                System.out.println("Readerul " + Thread.currentThread().getName() + " citeste");
                Thread.sleep(1000);

                // ne pregatim sa parasim zona critica
                mutex.acquire();

                // este cu un Reader mai putin in paralel care acceseaza resursa
                ReadCounter.dec();
                Thread.sleep(1000);

                // daca nu mai sunt readeri, putem permite lui Write sa ia zona pentru scriere
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
        //}
    }

}

class ReadCounter {
    private static int readcnt;

    public static void setValue(int value) {
        readcnt = value;
    }

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