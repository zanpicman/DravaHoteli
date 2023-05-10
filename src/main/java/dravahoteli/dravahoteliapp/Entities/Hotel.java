package dravahoteli.dravahoteliapp.Entities;

/***********************************************************************
 * Module:  Hotel.java
 * Author:  tilen
 * Purpose: Defines the Class Hotel
 ***********************************************************************/

import dravahoteli.dravahoteliapp.Seed.DB;

import java.util.*;

/**
 * @pdOid fc6c0300-b400-4960-9b8b-5d7909d7f68a
 */
public class Hotel {
    /**
     * @pdOid be9146fe-5f23-492d-a96b-c9614ae840d5
     */
    private int hid;
    /**
     * @pdOid adddab9a-a7cd-4245-8bd3-082b691cca24
     */
    private String lokacija;
    /**
     * @pdOid 77a0e7b6-a9e7-4921-b592-0d5a69f6e13f
     */
    private String ime;
    /**
     * @pdOid 5c18dc9d-e402-4a12-9221-7fedc2610b63
     */
    private int zvezdice;

    private String opis;

    /**
     * @pdRoleInfo migr=no name=Soba assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=1..* type=Composition
     */
    public java.util.Collection<Soba> soba;

    public Hotel() {
    }

    public Hotel(int hid, String lokacija, String ime, int zvezdice, String opis) {
        this.hid = hid;
        this.lokacija = lokacija;
        this.ime = ime;
        this.zvezdice = zvezdice;
        this.opis = opis;
    }

    public String getIme() {
        return ime;
    }

    /**
     * @pdOid 3da92d48-e5ab-4ade-bbcc-afce50d28459
     */
    public ArrayList<Soba> vrniSeznamSobHotela(int hid) {
        return DB.selectFromSobaWhereHid(hid);
    }

    /**
     * @pdOid 26d3f520-4ea9-402e-aea0-18624e93a476
     */
    public ArrayList<Hotel> vrniSeznamHotelov() {
        return DB.selectAllFromHotel();
    }


    /**
     * @pdGenerated default getter
     */
    public java.util.Collection<Soba> getSoba() {
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
        if (!this.soba.contains(newSoba)) {
            this.soba.add(newSoba);
            newSoba.setHotel(this);
        }
    }

    /**
     * @param oldSoba
     * @pdGenerated default remove
     */
    public void removeSoba(Soba oldSoba) {
        if (oldSoba == null)
            return;
        if (this.soba != null)
            if (this.soba.contains(oldSoba)) {
                this.soba.remove(oldSoba);
                oldSoba.setHotel((Hotel) null);
            }
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllSoba() {
        if (soba != null) {
            Soba oldSoba;
            for (java.util.Iterator iter = getIteratorSoba(); iter.hasNext(); ) {
                oldSoba = (Soba) iter.next();
                iter.remove();
                oldSoba.setHotel((Hotel) null);
            }
        }
    }

    public String getLokacija() {
        return lokacija;
    }

    public String getOpis() {
        return opis;
    }

    public int getZvezdice() {
        return zvezdice;
    }

    public int getHid() {
        return hid;
    }
}