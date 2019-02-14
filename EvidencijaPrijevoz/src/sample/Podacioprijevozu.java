package sample;

public class Podacioprijevozu{

    public final String Datum;
    public final String brojKmDolazak;
    public final String brojKmOdlazak;
    public final String prijevoznoSredstvo;


    public Podacioprijevozu(String Datum1, String Brojkmd1, String brojkmodlazak1, String prijevoz1){

        this.Datum = new String(Datum1);
        this.brojKmDolazak = new String(Brojkmd1);
        this.brojKmOdlazak = new String(brojkmodlazak1);
        this.prijevoznoSredstvo = new String(prijevoz1);
    }

    public String getDatum() { return Datum; }
    public String getBrojKmDolazak() {
        return brojKmDolazak;
    }
    public String getBrojKmOdlazak() { return brojKmOdlazak; }
    public String getPrijevoznoSredstvo() { return prijevoznoSredstvo; }

}