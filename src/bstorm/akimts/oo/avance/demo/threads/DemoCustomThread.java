package bstorm.akimts.oo.avance.demo.threads;

public class DemoCustomThread {

    public static void main(String[] args) {
        EcritureRecurente er = new EcritureRecurente("mon message", 1);
        er.start();
    }

}
