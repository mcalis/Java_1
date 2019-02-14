package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Zaposlenik {
    public final String imePrezimeZaposlenika;
    public final String adresaRada;
    public final String adresaStanovanja;
    /**
     *  listaPutovanja sadrži sva putovanja jednog zaposlenika
     */
    public ObservableList<podaciOPrijevozu> listaPutovanja= FXCollections.observableArrayList();

    public Zaposlenik(String imePrezimeZaposlenika, String adresaRada, String adresaStanovanja) {
        this.imePrezimeZaposlenika = imePrezimeZaposlenika;
        this.adresaRada = adresaRada;
        this.adresaStanovanja = adresaStanovanja;
    }
    public String getImePrezimeZaposlenika() {
        return imePrezimeZaposlenika;
    }

    public String getAdresaRada() {
        return adresaRada;
    }

    public String getAdresaStanovanja() {
        return adresaStanovanja;
    }

}
