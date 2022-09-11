import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// sa se porneasca un producator si un consumator astfel incat 
// producatorul parcurge recursiv o structura de foldere, plecand dintr-un nod radacina 
// si transmite toate fisierele .txt in zona partajata de memorie 
// (absolute path)
// consumatorul citeste numele fisierelor si aplica o modificare asupra lor 
// transforma prima litera din fiecare cuvant in majuscula, daca nu este deja 

// in implementarea curenta, Producatorul mai intai obtine lista tuturor fisierelor de prelucrat
// si abia dupa o transmite Consumatorului 
// dar scenariul asteptat este acela in care fiecare fisier este transmis 
// exact in momentul in care este gasit 

// 9p examen + 1p oficiu + 2p laborator 
// tema: 
// sa modificati programul astfel incat producatorul sa parcurga structura de foldere
// si sa modificati consumatorul, astfel incat sa aplice prelucrarea pe fisiere ceruta 
// bogdan.macovei.fmi@gmail.com

public class ProducerConsumerFiles {
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
        FileWalker fw = new FileWalker();
        ArrayList<String> files = fw.walk(".");

        for (String file : files) {
            System.out.format("Sending file %s...\n", file);
            drop.put(file);
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
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            try {
                System.out.format("Processing file %s...\n", message);

                File file = new File(message);
                Scanner reader = new Scanner(file);

                // de inlocuit cu modificarile cerute
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }

                reader.close();
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class FileWalker {
    public ArrayList<String> walk(String path) {
        ArrayList<String> files = new ArrayList<>();

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            return files; 
        }

        for (File f : list) {
            if (f.isDirectory()) {
                files.addAll(walk(f.getAbsolutePath()));
            }
            else if (f.getName().endsWith(".txt")) {
                files.add(f.getAbsolutePath());
            }
        }

        return files;
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