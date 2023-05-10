package dravahoteli.dravahoteliapp.Seed;

import dravahoteli.dravahoteliapp.Entities.Hotel;
import dravahoteli.dravahoteliapp.Entities.Rezervacija;
import dravahoteli.dravahoteliapp.Entities.Soba;
import dravahoteli.dravahoteliapp.Entities.Stranka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DB {
    public static ArrayList<Hotel> hoteli = new ArrayList<>();
    public static ArrayList<Soba> sobe = new ArrayList<>();
    public static ArrayList<Stranka> stranke = new ArrayList<>();
    public static ArrayList<Rezervacija> rezervacije = new ArrayList<>();

    public static int hid = 0;
    public static int sid = 0;
    public static int rid = 0;
    public static String GENERIC_DESCRIPTIONH1 = "Hotel se nahaja v središču mesta in ponuja razkošne sobe z moderno opremo. V sklopu hotela je na voljo tudi restavracija, kjer strežejo vrhunske jedi.";
    public static String GENERIC_DESCRIPTIONH2 = "Hotel se nahaja ob obali in ponuja čudovit razgled na morje. Sobe so prostorne in opremljene z balkoni, kjer si lahko privoščite kavo ob sončnem vzhodu. V sklopu hotela je na voljo tudi bazen in spa center.";

    public static String GENERIC_DESCRIPTION1 = "Soba je majhna, v njej se nahaja le postelja, omarica in mizica. Kljub temu pa je zelo prijetna in udobna, saj je opremljena z mehkimi blazinami in odejami.";
    public static String GENERIC_DESCRIPTION2 = "Ta soba je svetla in prostorna, z velikim oknom, ki omogoča veliko naravne svetlobe. V njej se nahaja velika postelja, garderobna omara in pisalna miza.";
    public static String GENERIC_DESCRIPTION3 = "Soba ima temno obarvane stene, ki ustvarjajo intimno in udobno vzdušje. V njej se nahaja udobna postelja, majhna knjižna polica in mizica za delo.";
    public static String GENERIC_DESCRIPTION4 = "Ta soba je opremljena v modernem stilu, z minimalističnim pohištvom in svetlimi barvami. V njej se nahaja velika postelja, garderobna omara in mizica za delo.\n";
    public static String GENERIC_DESCRIPTION5 = "Soba ima rustikalni videz, z lesenim pohištvom in toplimi barvami. V njej se nahaja velika postelja, garderobna omara in majhna miz";

    public static ArrayList<Rezervacija> posebneRezervacije = new ArrayList<>();

    @SuppressWarnings("all")
    public DB() {}

    public static void fillDatabase() {
        Hotel hotel1 = new Hotel(hid++, "Ožbalt", "Pr Orž'", 2, GENERIC_DESCRIPTIONH1);
        Hotel hotel2 = new Hotel(hid++, "Maribor", "Grand Mharibour", 5, GENERIC_DESCRIPTIONH2);
        Hotel hotel3 = new Hotel(hid++, "Zlatoličje", "Zlata lisica", 4, GENERIC_DESCRIPTIONH1);
        Hotel hotel4 = new Hotel(hid++, "Dravograd", "Drava in grad", 3, GENERIC_DESCRIPTIONH2);
        Hotel hotel5 = new Hotel(hid++, "Formin", "Foreminihue", 5, GENERIC_DESCRIPTIONH1);

        hoteli.add(hotel1);
        hoteli.add(hotel2);
        hoteli.add(hotel3);
        hoteli.add(hotel4);
        hoteli.add(hotel5);

        Soba soba1 = new Soba(sid++, 0, "Marjetica", 100, 5, GENERIC_DESCRIPTION1, false, 73,"/dravahoteli/dravahoteliapp/images/07c6ca159d4d7daa58ad04b6b4930ec1.jpg" );
        Soba soba2 = new Soba(sid++, 0, "Zvonček", 101, 2, GENERIC_DESCRIPTION2, false, 110,"/dravahoteli/dravahoteliapp/images/86e685af18659ee9ecca35c465603812.jpg");
        Soba soba3 = new Soba(sid++, 0, "Orhideja", 102, 2, GENERIC_DESCRIPTION3, false, 170,"/dravahoteli/dravahoteliapp/images/624b471bdf247131f10ea14f_61d31b8dbff9b500cbd7ed32_types_of_rooms_in_a_5-star_hotel_2_optimized_optimized.jpeg");
        Soba soba4 = new Soba(sid++, 1, "Smreka", 103, 3, GENERIC_DESCRIPTION5, false, 124,"/dravahoteli/dravahoteliapp/images/140127103345-peninsula-shanghai-deluxe-mock-up.jpg");
        Soba soba5 = new Soba(sid++, 1, "Bukev", 104, 4, GENERIC_DESCRIPTION4, false, 121,"/dravahoteli/dravahoteliapp/images/Andra2483-Andra-Queen-Queen.jpg");
        Soba soba6 = new Soba(sid++, 1, "Lipa", 105, 5, GENERIC_DESCRIPTION3, false, 314,"/dravahoteli/dravahoteliapp/images/07c6ca159d4d7daa58ad04b6b4930ec1.jpg");
        Soba soba7 = new Soba(sid++, 2, "Medved", 106, 10, GENERIC_DESCRIPTION5, false, 215,"/dravahoteli/dravahoteliapp/images/86e685af18659ee9ecca35c465603812.jpg");
        Soba soba8 = new Soba(sid++, 2, "Slon", 107, 8, GENERIC_DESCRIPTION1, false, 241,"/dravahoteli/dravahoteliapp/images/Andra2483-Andra-Queen-Queen.jpg");
        Soba soba9 = new Soba(sid++, 2, "Žirafa", 108, 2, GENERIC_DESCRIPTION4, false, 124,"/dravahoteli/dravahoteliapp/images/624b471bdf247131f10ea14f_61d31b8dbff9b500cbd7ed32_types_of_rooms_in_a_5-star_hotel_2_optimized_optimized.jpeg");
        Soba soba10 = new Soba(sid++, 3, "Tuna", 109, 2, GENERIC_DESCRIPTION2, false, 100,"/dravahoteli/dravahoteliapp/images/86e685af18659ee9ecca35c465603812.jpg");
        Soba soba11 = new Soba(sid++, 3, "Sardina", 110, 3, GENERIC_DESCRIPTION5, false, 170,"/dravahoteli/dravahoteliapp/images/140127103345-peninsula-shanghai-deluxe-mock-up.jpg");
        Soba soba12 = new Soba(sid++, 3, "Losos", 111, 4, GENERIC_DESCRIPTION2, false, 160,"/dravahoteli/dravahoteliapp/images/Andra2483-Andra-Queen-Queen.jpg");
        Soba soba13 = new Soba(sid++, 4, "Kokoš", 112, 5, GENERIC_DESCRIPTION3, false, 230,"/dravahoteli/dravahoteliapp/images/07c6ca159d4d7daa58ad04b6b4930ec1.jpg");
        Soba soba14 = new Soba(sid++, 4, "Holob", 113, 4, GENERIC_DESCRIPTION2, false, 84,"/dravahoteli/dravahoteliapp/images/86e685af18659ee9ecca35c465603812.jpg");
        Soba soba15 = new Soba(sid++, 4, "Sova", 114, 2, GENERIC_DESCRIPTION1, false, 415,"/dravahoteli/dravahoteliapp/images/624b471bdf247131f10ea14f_61d31b8dbff9b500cbd7ed32_types_of_rooms_in_a_5-star_hotel_2_optimized_optimized.jpeg");

        sobe.add(soba1);
        sobe.add(soba2);
        sobe.add(soba3);
        sobe.add(soba4);
        sobe.add(soba5);
        sobe.add(soba6);
        sobe.add(soba7);
        sobe.add(soba8);
        sobe.add(soba9);
        sobe.add(soba10);
        sobe.add(soba11);
        sobe.add(soba12);
        sobe.add(soba13);
        sobe.add(soba14);
        sobe.add(soba15);

        soba1.setHotel(hotel1);
        soba2.setHotel(hotel1);
        soba3.setHotel(hotel1);
        soba4.setHotel(hotel2);
        soba5.setHotel(hotel2);
        soba6.setHotel(hotel2);
        soba7.setHotel(hotel3);
        soba8.setHotel(hotel3);
        soba9.setHotel(hotel3);
        soba10.setHotel(hotel4);
        soba11.setHotel(hotel4);
        soba12.setHotel(hotel4);
        soba13.setHotel(hotel5);
        soba14.setHotel(hotel5);
        soba15.setHotel(hotel5);

        hotel1.addSoba(soba1);
        hotel1.addSoba(soba2);
        hotel1.addSoba(soba3);
        hotel2.addSoba(soba4);
        hotel2.addSoba(soba5);
        hotel2.addSoba(soba6);
        hotel3.addSoba(soba7);
        hotel3.addSoba(soba8);
        hotel3.addSoba(soba9);
        hotel4.addSoba(soba10);
        hotel4.addSoba(soba11);
        hotel4.addSoba(soba12);
        hotel5.addSoba(soba13);
        hotel5.addSoba(soba14);
        hotel5.addSoba(soba15);

        Stranka stranka1 = new Stranka(
                "1",
                "1",
                "Lojze",
                "Novak",
                LocalDate.now(),
                "+38612345678",
                "lojzenovak@hotmail.com",
                "Levo desno gor 12a"
        );

        stranke.add(stranka1);

        Rezervacija rezervacija1 = new Rezervacija(
                rid++,
                0,
                LocalDate.parse("2023-06-05"),
                LocalDate.parse("2023-06-15"),
                5,
                100,
                stranka1.getLoginID()

        );

        rezervacije.add(rezervacija1);
        soba1.addRezervacija(rezervacija1);
        stranka1.addRezervacija(rezervacija1);
    }

    public static ArrayList<Stranka> selectAllFromStranka() {
        return stranke;
    }

    public static ArrayList<Stranka> selectFromStrankaWhereLoginId(String loginId) {
        return new ArrayList<>();
    }

    public static ArrayList<Hotel> selectAllFromHotel() {
        return hoteli;
    }

    public static ArrayList<Hotel> selectFromHotelWhereHid(int hid) {
        return new ArrayList<>();
    }

    public static ArrayList<Soba> selectAllFromSoba() {
        return sobe;
    }

    public static ArrayList<Soba> selectFromSobaWhereSid(int sid) {
        return new ArrayList<>();
    }

    public static ArrayList<Rezervacija> selectAllFromRezervacija() {
        return rezervacije;
    }

    public static ArrayList<Rezervacija> selectFromRezervacijaWhereRid() {
        return new ArrayList<>();
    }

    public static ArrayList<Rezervacija> selectFromRezervacijaWhereLoginId() {
        return new ArrayList<>();
    }

    public static ArrayList<Rezervacija> selectFromRezervacijaWhereSid() {
        return new ArrayList<>();
    }

    public static ArrayList<Soba> selectFromSobaWhereHid(int hid) {
        ArrayList<Soba> arrayList = new ArrayList<>();
        for (Soba soba : sobe) {
            if (soba.getHid() == hid) arrayList.add(soba);
        }
        return arrayList;
    }

    public static boolean insertRezervacija(int sid, LocalDate datumOd, LocalDate datumDo, int steviloOseb, double znesek, String loginId) {
        rezervacije.add(new Rezervacija(rid++, sid, datumOd, datumDo, steviloOseb, znesek, loginId));
        return true;
    }


}
