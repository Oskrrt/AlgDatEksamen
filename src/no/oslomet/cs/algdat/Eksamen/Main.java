package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        EksamenSBinTre<String> test = new EksamenSBinTre<>(Comparator.naturalOrder());
        EksamenSBinTre<Integer> test2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        EksamenSBinTre<Character> test3 = new EksamenSBinTre<>(Comparator.naturalOrder());


        System.out.println(test.antall());
        System.out.println(test2.antall());
        System.out.println(test3.antall());
        Integer[] a = {3,7,2,9,3,10,8,7,4,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

       /* System.out.println(tre.antall()); // Utskrift: 10
        System.out.println(tre.antall(5)); // Utskrift: 0
        System.out.println(tre.antall(4)); // Utskrift: 3
        System.out.println(tre.antall(7)); // Utskrift: 21*/
        System.out.println(tre.antall(10)); // Utskrift:

        Character[] b = {'D', 'B', 'A', 'F', 'G', 'P', 'M', 'D'};
        EksamenSBinTre<Character> treChar = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (char verdi : b) treChar.leggInn(verdi);
        //System.out.println("Inneholder :" + treChar.antall('D'));


        //System.out.println(tre.toStringPostOrder());
        /*Oppgave<Integer> k = new Oppgave<Integer>() {
            @Override
            public void utførOppgave(Integer integer) {

            }
        };
        tre.postorden(k);*/
        EksamenSBinTre<Integer> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());

        int[] c = {1, 4, 1, 3, 1, 2, 1, 1};
        for (int verdi : c) tre2.leggInn(verdi);
        String s = tre2.toStringPostOrder();
        System.out.println(s);
        System.out.println(tre2.fjernAlle(1));
        s = tre2.toStringPostOrder();
        System.out.println(s);


        EksamenSBinTre<Integer> tre3 = new EksamenSBinTre<>(Comparator.naturalOrder());

        for (int verdi : a) tre3.leggInn(verdi);
        System.out.println("før nullstill: "+tre.toStringPostOrder());
        tre.nullstill();
        System.out.println("etter nullstill: "+tre.toStringPostOrder());

        // oppgave 4

        tre.postorden(new Oppgave<Integer>() {
            @Override
            public void utførOppgave(Integer integer) {
                System.out.println(integer);
            }
        });

    }
}
