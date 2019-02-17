package sample;

import java.time.LocalDate;

public class EvidencijaPodaci {
    public final LocalDate Datumevidencija;
    public final String Opcija;
    public final String Potpis;

    EvidencijaPodaci(LocalDate Datumevidencija1, String Opcija1){
        this.Datumevidencija = Datumevidencija1;
        this.Opcija = Opcija1;
        this.Potpis = "";
    }

    public LocalDate getDatumevidencija() {
        return Datumevidencija;
    }

    public String getOpcija() {
        return Opcija;
    }

    public String getPotpis() {
        return Potpis;
    }
}
