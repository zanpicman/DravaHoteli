package dravahoteli.dravahoteliapp.Controllers;

import dravahoteli.dravahoteliapp.Boundaries.SVBancniSistem_SIM;
import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Rezervacija;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;
import dravahoteli.dravahoteliapp.Seed.DB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class KOpraviRezervacijo {

    public SVBancniSistem_SIM sVBancniSistemSIM;

    public HashSet<Stranka> stranka;

    public HashSet<Rezervacija> rezervacija;

    public HashSet<Soba> soba;

    public HashSet<Hotel> hotel;


    public Stranka prijaviUporabnika(String uporabniskoIme, String geslo) {
        Stranka strankaObject = new Stranka();
        return strankaObject.preveriPrijavo(uporabniskoIme, geslo);

    }


    public ArrayList<Hotel> vrniSeznamHotelov() {
        Hotel hotelObject = new Hotel();
        return hotelObject.vrniSeznamHotelov();
    }


    public ArrayList<Soba> vrniSeznamSobHotela(int hid) {
        Hotel hotelObject = new Hotel();
        return hotelObject.vrniSeznamSobHotela(hid);
    }


    public java.lang.Object[] prikaziPodrobnostiSobe() {
        // TODO: implement
        return null;
    }

    public java.lang.Object[] prikaziRazporozljivostSobe() {
        // TODO: implement
        return null;
    }


    public boolean opraviRezervacijo(String ime,
                                     String priimek,
                                     String stevilkaKartice,
                                     String stevilkaCcv,
                                     String mesecPoteka,
                                     String letoPoteka,
                                     LocalDate datumOd,
                                     LocalDate datumDo,
                                     int steviloOseb,
                                     double znesek,
                                     String loginId,
                                     int sid) {
        SVBancniSistem_SIM svBancniSistem_sim = new SVBancniSistem_SIM();
        boolean opravljenoPlacilo = svBancniSistem_sim.placaj(ime, priimek, stevilkaKartice, stevilkaCcv, mesecPoteka, letoPoteka);
        if (!opravljenoPlacilo) return false;

        // če je bilo plačilo bilo uspešno ga shrani v DB
        Rezervacija rezervacija1 = new Rezervacija();
        return rezervacija1.dodajRezervacijo(sid, datumOd, datumDo, steviloOseb, znesek, loginId);
    }


    /**
     * @pdGenerated default getter
     */
    public java.util.Collection getStranka() {
        if (stranka == null)
            stranka = new java.util.HashSet();
        return stranka;
    }

    /**
     * @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorStranka() {
        if (stranka == null)
            stranka = new java.util.HashSet();
        return stranka.iterator();
    }

    /**
     * @param newStranka
     * @pdGenerated default setter
     */
    public void setStranka(java.util.Collection newStranka) {
        removeAllStranka();
        for (java.util.Iterator iter = newStranka.iterator(); iter.hasNext(); )
            addStranka((Stranka) iter.next());
    }

    /**
     * @param newStranka
     * @pdGenerated default add
     */
    public void addStranka(Stranka newStranka) {
        if (newStranka == null)
            return;
        if (this.stranka == null)
            this.stranka = new java.util.HashSet();
        if (!this.stranka.contains(newStranka))
            this.stranka.add(newStranka);
    }

    /**
     * @param oldStranka
     * @pdGenerated default remove
     */
    public void removeStranka(Stranka oldStranka) {
        if (oldStranka == null)
            return;
        if (this.stranka != null)
            if (this.stranka.contains(oldStranka))
                this.stranka.remove(oldStranka);
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllStranka() {
        if (stranka != null)
            stranka.clear();
    }

    /**
     * @pdGenerated default getter
     */
    public java.util.Collection getRezervacija() {
        if (rezervacija == null)
            rezervacija = new java.util.HashSet();
        return rezervacija;
    }

    /**
     * @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorRezervacija() {
        if (rezervacija == null)
            rezervacija = new java.util.HashSet();
        return rezervacija.iterator();
    }

    /**
     * @param newRezervacija
     * @pdGenerated default setter
     */
    public void setRezervacija(java.util.Collection newRezervacija) {
        removeAllRezervacija();
        for (java.util.Iterator iter = newRezervacija.iterator(); iter.hasNext(); )
            addRezervacija((Rezervacija) iter.next());
    }

    /**
     * @param newRezervacija
     * @pdGenerated default add
     */
    public void addRezervacija(Rezervacija newRezervacija) {
        if (newRezervacija == null)
            return;
        if (this.rezervacija == null)
            this.rezervacija = new java.util.HashSet();
        if (!this.rezervacija.contains(newRezervacija))
            this.rezervacija.add(newRezervacija);
    }

    /**
     * @param oldRezervacija
     * @pdGenerated default remove
     */
    public void removeRezervacija(Rezervacija oldRezervacija) {
        if (oldRezervacija == null)
            return;
        if (this.rezervacija != null)
            if (this.rezervacija.contains(oldRezervacija))
                this.rezervacija.remove(oldRezervacija);
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllRezervacija() {
        if (rezervacija != null)
            rezervacija.clear();
    }

    /**
     * @pdGenerated default getter
     */
    public java.util.Collection getSoba() {
        if (soba == null)
            soba = new java.util.HashSet();
        return soba;
    }

    /**
     * @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorSoba() {
        if (soba == null)
            soba = new java.util.HashSet();
        return soba.iterator();
    }

    /**
     * @param newSoba
     * @pdGenerated default setter
     */
    public void setSoba(java.util.Collection newSoba) {
        removeAllSoba();
        for (java.util.Iterator iter = newSoba.iterator(); iter.hasNext(); )
            addSoba((Soba) iter.next());
    }

    /**
     * @param newSoba
     * @pdGenerated default add
     */
    public void addSoba(Soba newSoba) {
        if (newSoba == null)
            return;
        if (this.soba == null)
            this.soba = new java.util.HashSet();
        if (!this.soba.contains(newSoba))
            this.soba.add(newSoba);
    }

    /**
     * @param oldSoba
     * @pdGenerated default remove
     */
    public void removeSoba(Soba oldSoba) {
        if (oldSoba == null)
            return;
        if (this.soba != null)
            if (this.soba.contains(oldSoba))
                this.soba.remove(oldSoba);
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllSoba() {
        if (soba != null)
            soba.clear();
    }

    /**
     * @pdGenerated default getter
     */
    public java.util.Collection getHotel() {
        if (hotel == null)
            hotel = new java.util.HashSet();
        return hotel;
    }

    /**
     * @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorHotel() {
        if (hotel == null)
            hotel = new java.util.HashSet();
        return hotel.iterator();
    }

    /**
     * @param newHotel
     * @pdGenerated default setter
     */
    public void setHotel(java.util.Collection newHotel) {
        removeAllHotel();
        for (java.util.Iterator iter = newHotel.iterator(); iter.hasNext(); )
            addHotel((Hotel) iter.next());
    }

    /**
     * @param newHotel
     * @pdGenerated default add
     */
    public void addHotel(Hotel newHotel) {
        if (newHotel == null)
            return;
        if (this.hotel == null)
            this.hotel = new java.util.HashSet();
        if (!this.hotel.contains(newHotel))
            this.hotel.add(newHotel);
    }

    /**
     * @param oldHotel
     * @pdGenerated default remove
     */
    public void removeHotel(Hotel oldHotel) {
        if (oldHotel == null)
            return;
        if (this.hotel != null)
            if (this.hotel.contains(oldHotel))
                this.hotel.remove(oldHotel);
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllHotel() {
        if (hotel != null)
            hotel.clear();
    }

}