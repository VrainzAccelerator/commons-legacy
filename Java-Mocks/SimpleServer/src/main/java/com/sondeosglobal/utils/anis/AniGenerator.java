package com.sondeosglobal.utils.anis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AniGenerator {
    long piso = 5499550000l;
    long techo = 5499559999l;

    Random rand = new Random();

    public long getNextLong() {
        long number = piso + ((long) (rand.nextDouble() * (techo - piso)));
        return number;
    }

    public List<String> get10KAnis() {
        int cantAnis = 10000;
        return getAnis(cantAnis);
    }

    public List<String> getTenAnis() {
        int cantAnis = 10;
        return getAnis(cantAnis);
    }

    public List<String> getAnis(int quantity) {
        List<String> anis = new ArrayList<String>();
        for (int i = 0; i < quantity; i++) {
            long ani = getNextLong();
            anis.add(String.valueOf(ani));
        }

        return anis;
    }


    public List<String> get500KAnis() {
        int cantAnis = 500000;
        return getAnis(cantAnis);
    }
}
