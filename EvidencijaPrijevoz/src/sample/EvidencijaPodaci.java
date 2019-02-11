package sample;

public class EvidencijaPodaci {
    public final String Mjesec;
    public final String Dan;
    public final String Opcija;

    EvidencijaPodaci(String Mjesec1, String Dan1, String Opcija1){
        this.Mjesec = new String(Mjesec1);
        this.Dan = new String(Dan1);
        this.Opcija = new String(Opcija1);
    }

    public String getDan() {
        return Dan;
    }

    public String getMjesec() {
        return Mjesec;
    }

    public String getOpcija() {
        return Opcija;
    }
}
