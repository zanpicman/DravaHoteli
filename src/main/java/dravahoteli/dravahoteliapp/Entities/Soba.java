package dravahoteli.dravahoteliapp.Entities; /***********************************************************************
 * Module:  Soba.java
 * Author:  EnejM
 * Purpose: Defines the Class Soba
 ***********************************************************************/

import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Rezervacija;

import java.util.*;

/** @pdOid 2454bb53-a0a3-4ab5-9a21-484f78785e71 */
public class Soba {
    /** @pdOid 3284b80b-9bcf-47b3-8bfc-6c4e236d4318 */
    private int sid;

    // As a foreign key
    private int hid;

    private String ime;

    private String url;

    private int stevilkaSobe;

    private int steviloPostelj;

    private String opis;

    private boolean balkon;

    private  double cenaDan;


    /** @pdRoleInfo migr=no name=Rezervacija assc=association8 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
    public HashSet<Rezervacija> rezervacija;
    /** @pdRoleInfo migr=no name=Hotel assc=association9 mult=1 side=A */
    public Hotel hotel;

    public Soba() {}

    public Soba(int sid, int hid, String ime, int stevilkaSobe, int steviloPostelj, String opis, boolean balkon, double cenaDan, String url) {
        this.sid = sid;
        this.hid = hid;
        this.ime = ime;
        this.stevilkaSobe = stevilkaSobe;
        this.steviloPostelj = steviloPostelj;
        this.opis = opis;
        this.balkon = balkon;
        this.cenaDan = cenaDan;
        this.url = url;
    }

    /** @pdOid 48399b9e-18a3-4031-9d55-1ddaab834a2e */
    public java.lang.Object[] vrniSeznamSobeHotela() {
        // TODO: implement
        return null;
    }

    /** @pdOid cd3f798a-7d68-4cb6-9bc1-fc7f5e5946b8 */
    public java.lang.Object vrniPodronostiSobe() {
        // TODO: implement
        return null;
    }

    /** @pdOid 88d8ea5c-6822-4820-9bc6-f2ee4a5cbd67 */
    public java.lang.Object[] vrniRazporozljivost() {
        // TODO: implement
        return null;
    }


    /** @pdGenerated default getter */
    public HashSet<Rezervacija> getRezervacija() {
        if (rezervacija == null)
            rezervacija = new HashSet<>();
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
    /** @pdGenerated default parent getter */
    public Hotel getHotel() {
        return hotel;
    }

    /** @pdGenerated default parent setter
     * @param newHotel */
    public void setHotel(Hotel newHotel) {
        if (this.hotel == null || !this.hotel.equals(newHotel))
        {
            if (this.hotel != null)
            {
                Hotel oldHotel = this.hotel;
                this.hotel = null;
                oldHotel.removeSoba(this);
            }
            if (newHotel != null)
            {
                this.hotel = newHotel;
                this.hotel.addSoba(this);
            }
        }
    }

    public int getHid() {
        return hid;
    }

    public String getIme() {
        return ime;
    }

    public int getStevilkaSobe() {
        return stevilkaSobe;
    }

    public int getSteviloPostelj() {
        return steviloPostelj;
    }

    public String getOpis() {
        return opis;
    }

    public boolean isBalkon() {
        return balkon;
    }

    public double getCenaDan() {
        return cenaDan;
    }

    public String getUrl() {
        return url;
        }
    public int getSid() {
        return sid;
    }
}
