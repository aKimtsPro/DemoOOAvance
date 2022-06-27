package bstorm.akimts.oo.avance.demo.lambdas;

public interface Vol {

    boolean voler(int distance, int hauteur);

    default void atterir(){
        System.out.println("j'atterri");
    }

    static void truc(){
        System.out.println("truc");
    }

}
