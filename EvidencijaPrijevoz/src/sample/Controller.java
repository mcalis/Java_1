package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class Controller{

    public void initialize () {
        //Mora se unesti barem jedan zaposlenik, nakon unošenja gumb unosPutovanja postaje dostupan.
        unosPutovanja.setDisable(true);
    }

    /**
     * U listu listaZaposlenika spremaju se svi Zaposlenici
     */
    private ObservableList<Zaposlenik> listaZaposlenika = FXCollections.observableArrayList();

    // pomocna lista stringova za comboBox
    private ObservableList<String> listaImena = FXCollections.observableArrayList();

    //za prijevoznoSredstvo
    //tablica
    FXMLLoader loader = new FXMLLoader(Controller.class.getResource("C:\\Users\\David\\Desktop\\EvidencijaPrijevoz\\src\\sample\\sample.fxml"));
    @FXML
    TableView<podaciOPrijevozu> tablePrijevoz;
    @FXML
    TableColumn<podaciOPrijevozu, String> DatumStupac;
    @FXML
     TableColumn <podaciOPrijevozu, String> KmDolazakStupac;
    @FXML
    TableColumn<podaciOPrijevozu, String> KmOdlazakStupac;
    @FXML
    TableColumn<podaciOPrijevozu, String> prijevozStupac;
    @FXML
    TableColumn<podaciOPrijevozu, String> potpisStupac;

    //textField za unos podataka o prijevozu - podaciOPrijevozu
    @FXML
    TextField textFieldImePrezimeZaposlenika;
    @FXML
    ComboBox comboBoxZaposlenik;

    @FXML
    TextField textFieldAdresaStanovanja;
    @FXML
    TextField textFieldAdresaRada;
    @FXML
    TextField textFieldDatumPrijevoz;
    @FXML
    TextField textFieldKMdolazakPrijevoz;
    @FXML
    TextField textFieldKModlazakPrijevoz;
    @FXML
    TextField textFieldSredstvoPrijevoz;
    @FXML
    Button unosPutovanja;
    @FXML
    Button buttonUnosZaposlenika;
    @FXML
    Button buttonOcistiCelijeZaposlenik;

    //za evidenciju
    @FXML
    TextField brmjesece;
    @FXML
    TextField brdane;
    @FXML
    TextField bropise;
    @FXML
    Button unosevidencija;
    @FXML
    private void buttonOcistiCelijeZaposlenik (ActionEvent event) {
        textFieldImePrezimeZaposlenika.clear();
        textFieldAdresaRada.clear();
        textFieldAdresaStanovanja.clear();
        unosPutovanja.setDisable(true);
        buttonUnosZaposlenika.setDisable(false);
    }

    @FXML
    private void unosZaposlenikaGumb (ActionEvent event)  {
        if (textFieldImePrezimeZaposlenika.getText().equalsIgnoreCase("") || textFieldAdresaStanovanja.getText().equalsIgnoreCase("") || textFieldAdresaRada.getText().equalsIgnoreCase("")){
            upozorenje();
        }
        else {
            Zaposlenik noviZaposlenik = new Zaposlenik(textFieldImePrezimeZaposlenika.getText(),textFieldAdresaRada.getText(),textFieldAdresaStanovanja.getText());
            listaZaposlenika.add(noviZaposlenik);
            listaImena.add(textFieldImePrezimeZaposlenika.getText());
            comboBoxZaposlenik.setItems(listaImena);

            //Mora se unesti barem jedan zaposlenik, onda se omogući unos putovanja, tek onda za njega putovanja
            unosPutovanja.setDisable(false);

            // ove četiri linije ispod u ovoj metodi, su samo refresh tablice na prazno nakon unosa novog zaposlenika
            String odabraniZaposlenikString = textFieldImePrezimeZaposlenika.getText();
            Zaposlenik trazeni = listaZaposlenika.stream()
                    .filter(Zaposlenik -> odabraniZaposlenikString.equalsIgnoreCase(Zaposlenik.getImePrezimeZaposlenika())).findAny().orElse(null);
            tablePrijevoz.setItems(trazeni.listaPutovanja);
            buttonUnosZaposlenika.setDisable(true);
        }
    }
    /**
     * Na promjenu selekcije Zaposlenika u comboBoxu, refresha kompletni prikaz
     * @param event
     */
    @FXML
    private void ComboBoxChanged (ActionEvent event) {
        String odabraniZaposlenikString  = comboBoxZaposlenik.getValue().toString();
        Zaposlenik trazeni = listaZaposlenika.stream()
                .filter(Zaposlenik -> odabraniZaposlenikString.equalsIgnoreCase(Zaposlenik.getImePrezimeZaposlenika())).findAny().orElse(null);
        textFieldImePrezimeZaposlenika.setText(trazeni.getImePrezimeZaposlenika());
        textFieldAdresaRada.setText(trazeni.getAdresaRada());
        textFieldAdresaStanovanja.setText(trazeni.getAdresaStanovanja());
        tablePrijevoz.setItems(trazeni.listaPutovanja);
        unosPutovanja.setDisable(false);
    }
    /**
     * Ispis okvira s upozorenjem
     */
    public void upozorenje () {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Upozorenje");
        alert.setHeaderText("Upozorenje - kriv unos");
        alert.setContentText("Unesite sve što se traži.");
        alert.showAndWait().ifPresent(rs -> {
        });
    }

    /**
     * spremanje podataka o jednom putovanju kad se klikne gumb Unesi
     * */
   @FXML
   private void spremanjeprijevoza(){
       if (textFieldDatumPrijevoz.getText().equalsIgnoreCase("") || textFieldKMdolazakPrijevoz.getText().equalsIgnoreCase("") || textFieldKModlazakPrijevoz.getText().equalsIgnoreCase("") || textFieldSredstvoPrijevoz.getText().equalsIgnoreCase("")){
            upozorenje();
       }
       else {
           podaciOPrijevozu objektPodaciOPrijevozu = new podaciOPrijevozu(textFieldDatumPrijevoz.getText(),  textFieldKMdolazakPrijevoz.getText(),  textFieldKModlazakPrijevoz.getText(),  textFieldSredstvoPrijevoz.getText());

           // pronalazi zaposlenika čije ime je trenutno u textFieldu
           String odabraniZaposlenikString = textFieldImePrezimeZaposlenika.getText();
           Zaposlenik trazeni = listaZaposlenika.stream()
                   .filter(Zaposlenik -> odabraniZaposlenikString.equalsIgnoreCase(Zaposlenik.getImePrezimeZaposlenika())).findAny().orElse(null);

           // dodaje jedno putovanje za zaposlenika koji se nalazi odabran - trenutni u textFieldu
           trazeni.listaPutovanja.add(objektPodaciOPrijevozu);


           //primjer postavljanje podataka u tablicu
           DatumStupac.setCellValueFactory(
                   new PropertyValueFactory<>("Datum")
           );
           KmDolazakStupac.setCellValueFactory(
                   new PropertyValueFactory<>("brojKmDolazak")
           );
           KmOdlazakStupac.setCellValueFactory(
                   new PropertyValueFactory<>("brojKmOdlazak")
           );
           prijevozStupac.setCellValueFactory(
                   new PropertyValueFactory<>("prijevoznoSredstvo")
           );
           potpisStupac.setCellValueFactory(
                   new PropertyValueFactory<>("potpisStupac")
           );

           // puni tablicu putovanja za odabranog zaposlenika
           tablePrijevoz.setItems(trazeni.listaPutovanja);

           // brisanje textFielda nakon unosa
           textFieldDatumPrijevoz.clear();
           textFieldKMdolazakPrijevoz.clear();
           textFieldKModlazakPrijevoz.clear();
           textFieldSredstvoPrijevoz.clear();
       }
    }
    /**
     * spremanje podataka evidencija kad kliknemo gumb
     * @param event
     */
    @FXML
    private void pressButton(ActionEvent event){
        if(brdane.getText() != null && brmjesece.getText() != null && bropise.getText() != null){
        EvidencijaPodaci ep= new EvidencijaPodaci(brmjesece.getText(), brdane.getText(), bropise.getText());
            System.out.println(brmjesece.getText());
            System.out.println(brdane.getText());
            System.out.println(bropise.getText());
        }else{
            System.out.println("enter");
        }

    }

}
