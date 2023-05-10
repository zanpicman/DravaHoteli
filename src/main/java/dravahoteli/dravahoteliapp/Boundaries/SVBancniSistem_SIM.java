package dravahoteli.dravahoteliapp.Boundaries;

import java.time.LocalDate;

public class SVBancniSistem_SIM {
    public boolean placaj(String ime,
                          String priimek,
                          String stevilkaKartice,
                          String stevilkaCcv,
                          String mesecPoteka,
                          String letoPoteka) {
        //Do something hard
        int x = 0;
        for (int i = 0; i < 10000; i++) {
            if (x % 2 == 0) x++;
            else if (x % 2 == 1) x--;
        }
        return true;
    }
}
