package dravahoteli.dravahoteliapp.Entities;

/***********************************************************************
 * Module:  Stranka.java
 * Author:  tilen
 * Purpose: Defines the Class Stranka
 ***********************************************************************/

import dravahoteli.dravahoteliapp.Seed.DB;

import java.time.LocalDate;
import java.util.*;

public class Stranka {
    private String loginID;
    private String geslo;
    private String ime;
    private String priimek;
    private LocalDate datumRojstva;
    private String telefon;
    private String gmail;
    private String domaciNaslov;

    public java.util.Collection rezervacija;

    public Stranka() {}

    public Stranka(String loginID, String geslo, String ime, String priimek,
                   LocalDate datumRojstva, String telefon, String gmail, String domaciNaslov) {
        this.loginID = loginID;
        this.geslo = geslo;
        this.ime = ime;
        this.priimek = priimek;
        this.datumRojstva = datumRojstva;
        this.telefon = telefon;
        this.gmail = gmail;
        this.domaciNaslov = domaciNaslov;
    }

    public String getLoginID() {
        return loginID;
    }

    public static HashMap<String, String> podatkiStranke(String loginID) {
        return new HashMap<>();
    }

    /**
     * @pdOid e4a3964b-9142-4e14-aa14-f58ce4bb672c
     */
    public Stranka preveriPrijavo(String uporabniskoIme, String geslo) {

        // SIMULACIJA SELECT STAVKA
        ArrayList<Stranka> stranke = DB.selectAllFromStranka();
        for (Stranka stranka: stranke) {
            if (stranka.loginID.equals(uporabniskoIme) && stranka.geslo.equals(geslo)) {
                return stranka;
            }
        }
        return null;
    }

    /**
     * @pdOid e1a80801-1da5-415a-8403-554519e34336
     */
    public int onemogiciUporabnika() {
        // TODO: implement
        return 0;
    }


    /** @pdGenerated default getter */
    public java.util.Collection getRezervacija() {
        if (rezervacija == null)
            rezervacija = new java.util.HashSet();
        return rezervacija;
    }

    /** @pdGenerated default iterator getter */
    public java.util.Iterator getIteratorRezervacija() {
        if (rezervacija == null)
            rezervacija = new java.util.HashSet();
        return rezervacija.iterator();
    }

    /** @pdGenerated default setter
     * @param newRezervacija */
    public void setRezervacija(java.util.Collection newRezervacija) {
        removeAllRezervacija();
        for (java.util.Iterator iter = newRezervacija.iterator(); iter.hasNext();)
            addRezervacija((Rezervacija)iter.next());
    }

    /** @pdGenerated default add
     * @param newRezervacija */
    public void addRezervacija(Rezervacija newRezervacija) {
        if (newRezervacija == null)
            return;
        if (this.rezervacija == null)
            this.rezervacija = new java.util.HashSet();
        if (!this.rezervacija.contains(newRezervacija))
            this.rezervacija.add(newRezervacija);
    }

    /** @pdGenerated default remove
     * @param oldRezervacija */
    public void removeRezervacija(Rezervacija oldRezervacija) {
        if (oldRezervacija == null)
            return;
        if (this.rezervacija != null)
            if (this.rezervacija.contains(oldRezervacija))
                this.rezervacija.remove(oldRezervacija);
    }

    /** @pdGenerated default removeAll */
    public void removeAllRezervacija() {
        if (rezervacija != null)
            rezervacija.clear();
    }


    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public LocalDate getDatumRojstva() {
        return datumRojstva;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getGmail() {
        return gmail;
    }

    public String getDomaciNaslov() {
        return domaciNaslov;
    }
}