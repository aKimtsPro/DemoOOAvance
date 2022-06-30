package bstorm.akimts.oo.avance.demo.threads;

public class Demo {

    // wait peut être utilisé
    public synchronized void startWaiting() throws InterruptedException {
        System.out.println("Debut d'attente");
        this.wait(); // attend indefiniment que la clef soit notifiée
//        this.wait(8000); // attend max 8sec
        System.out.println("attente de 3sec");
        Thread.sleep(3000);
        System.out.println("Fin d'attente");
    }

    public synchronized void stopWaiting(){
        System.out.println("notification de la key");
        this.notify(); // un des thread en attente que la clef soit notifiée se réveille
        // this.notifyAll(); // tous les thread en attente sont réveillés
        // une fois le notify executé, le thread continue son travail sans bloquer
        System.out.println("fin de notification");
    }

}
