package sample;

public class Podacioprijevozu {
    public final String Datum;
    public final String Brojkmd;
    public final String brojkmodlazak;
    public final String prijevoz;
    public final String potpis;

    Podacioprijevozu(String Datum1, String Brojkmd1, String brojkmodlazak1, String prijevoz1){
        this.Datum = new String(Datum1);
        this.Brojkmd = new String(Brojkmd1);
        this.brojkmodlazak = new String(brojkmodlazak1);
        this.prijevoz = new String(prijevoz1);
        this.potpis= new String("");
    }

    public String getDatum() { return Datum; }
    public String getBrojkmd() {
        return Brojkmd;
    }
    public String getBrojkmodlazak() { return brojkmodlazak; }
    public String getPrijevoz() { return prijevoz; }
    public String getPotpis(){ return potpis; }
}