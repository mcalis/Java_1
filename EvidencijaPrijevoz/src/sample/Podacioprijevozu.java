package sample;

import java.time.LocalDate;
import java.util.Date;

public class Podacioprijevozu{

    public final LocalDate Datum;
    public final int brojKmDolazak;
    public final int brojKmOdlazak;
    public final String prijevoznoSredstvo;


    public Podacioprijevozu(LocalDate Datum1, int Brojkmd1, int brojkmodlazak1, String prijevoz1){

        this.Datum = Datum1;
        this.brojKmDolazak =  Brojkmd1;
        this.brojKmOdlazak =  brojkmodlazak1;
        this.prijevoznoSredstvo = new String(prijevoz1);
    }

    public LocalDate getDatum() { return Datum; }
    public int getBrojKmDolazak() {
        return brojKmDolazak;
    }
    public int getBrojKmOdlazak() { return brojKmOdlazak; }
    public String getPrijevoznoSredstvo() { return prijevoznoSredstvo; }

}