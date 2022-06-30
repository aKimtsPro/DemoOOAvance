package bstorm.akimts.oo.avance.demo.threads;

public class EcritureRecurente extends Thread{

    private String message;
    private int secondes;

    public EcritureRecurente(String message, int secondes) {
        this.message = message;
        this.secondes = secondes;
    }

    @Override
    public void run() {
        try {
            while(true){
                Thread.sleep(secondes * 1000L);
                System.out.println(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
