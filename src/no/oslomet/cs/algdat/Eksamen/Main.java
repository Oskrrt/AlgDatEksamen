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


    }
}
