package dravahoteli.dravahoteliapp.Entities;

/***********************************************************************
 * Module:  Rezervacija.java
 * Author:  tilen
 * Purpose: Defines the Class Rezervacija
 ***********************************************************************/

import dravahoteli.dravahoteliapp.Seed.DB;

import java.time.LocalDate;
import java.util.*;

/**
 * @pdOid 638bdc27-cd8c-42f8-828e-6f345e918db7
 */
public class Rezervacija {
    /**
     * @pdOid 5af07e10-5e96-4f25-bf9f-5dfdb484375a
     */
    private int rid;

    // Foreign key Stranka
    private String loginId;
    // Foreign key Soba
    private int sid;

    /**
     * @pdOid a750856c-14f1-430f-8bd4-d7e27901cf09
     */
    private LocalDate datumOd;
    /**
     * @pdOid 7b53a30c-221b-4800-bb04-906ebe793292
     */
    private LocalDate datumDo;
    /**
     * @pdOid afcc6555-108f-40ab-b8cc-82bb967f3f91
     */
    private int steviloOseb;
    // Added manually
    private double znesek;


    public Rezervacija() {
    }

    public Rezervacija(int rid, int sid, LocalDate datumOd, LocalDate datumDo, int steviloOseb, double znesek, String loginId) {
        this.rid = rid;
        this.sid = sid;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.steviloOseb = steviloOseb;
        this.znesek = znesek;
        this.loginId = loginId;
    }

    /**
     * @pdOid 3e6131c7-5153-4c62-90e9-717ad57f90bb
     */
    public boolean dodajRezervacijo(int sid, LocalDate datumOd,
                                    LocalDate datumDo,
                                    int steviloOseb,
                                    double znesek,
                                    String loginId) {
        // TODO: kaj pa način plačila?
        // TODO: kaj pa če je s kartico, kaj potem? lahko null ali brez null...
        return DB.insertRezervacija(sid, datumOd, datumDo, steviloOseb, znesek, loginId);
    }


    public LocalDate getDatumOd() {
        return datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }
}