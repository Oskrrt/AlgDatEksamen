package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        EksamenSBinTre<String> test = new EksamenSBinTre<>(Comparator.naturalOrder());
        EksamenSBinTre<Integer> test2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        EksamenSBinTre<Character> test3 = new EksamenSBinTre<>(Comparator.naturalOrder());


        System.out.println(test.antall());
        System.out.println(test2.antall());
        System.out.println(test3.antall());
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
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
    }
}
