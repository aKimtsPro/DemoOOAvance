package bstorm.akimts.oo.avance.demo;

import java.util.Date;


public class StringBizarre {

    public static void main(String[] args) {

//        String a = "salut";
//        String b = "salut";
//        String c = new String("salut");
//        String d = "sa"+"lut";
//
//        System.out.println(a == d); //
//
//        Integer z = 1;
//        Integer y = 1;
//
//        System.out.println(z == y);
//
//
//
//        Date date = new Date( Date.parse("2022-10-10") );
//
//        date = null;
//
//
//        Date date2 = (Date)date.clone();

        method();

    }

    static void method(){

        try {
//            return;
            throw new Exception();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            System.out.println("salut");
        }

    }


}
