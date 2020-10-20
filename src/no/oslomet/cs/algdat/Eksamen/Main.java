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

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.antall());

    }
}
