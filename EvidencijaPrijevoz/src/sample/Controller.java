package sample;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Optional;


public class Controller{


    //globalne varijable za generiranje PDFa
    String[] Ptrijevoznosredstvo = new String[40];
    String[] DatumPDF= new String[40];
    String[] KMDPDF= new String[40];
    String[] KMOPDF= new String[40];
    int BrojPDF=0;



    public void initialize () {
        //Mora se unesti barem jedan zaposlenik, nakon unošenja gumb unosPutovanja postaje dostupan.
        unosPutovanja.setDisable(true);
        datePickerDatePrijevoz.setDisable(true);
        textFieldKModlazakPrijevoz.setDisable(true);
        textFieldKMdolazakPrijevoz.setDisable(true);
        textFieldSredstvoPrijevoz.setDisable(true);
        // početno postavljanje današnjeg datuma
        datePickerZaposlenikMjesec.setValue(LocalDate.now());

        //primjer postavljanje stupaca u tablicu
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

        // ograničavanje odabira datuma do današnjeg dana
        DatePicker maxDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
        maxDate.setValue(LocalDate.now()); // Max date available will be 2015-01-01
        final Callback<DatePicker, DateCell> dayCellFactory;
        dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isAfter(maxDate.getValue())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
                }
            }
        };
        datePickerDatePrijevoz.setDayCellFactory(dayCellFactory); // ograničavanje odabira datuma do današnjeg datuma

        // ograničenje odabira dana u mjesecu na 15. u mjesecu, malo je intuitivnije :)
        final Callback<DatePicker, DateCell> dayCellFactoryOdabirMjeseca;
        dayCellFactoryOdabirMjeseca = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.getDayOfMonth() <15 || item.getDayOfMonth() >15 ) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
                }
            }
        };
        datePickerZaposlenikMjesec.setDayCellFactory((dayCellFactoryOdabirMjeseca));  // ograničenje odabira dana u mjesecu na 15. u mjesecu, malo je intuitivnije :)
    }
    /**
     * U listu listaZaposlenika spremaju se svi Zaposlenici
     */
    private ObservableList<Zaposlenik> listaZaposlenika = FXCollections.observableArrayList();
    // listaPutovanjaPojedinogMjeseca sprema sva putovanja od jednog mjeseca
    private ObservableList<Podacioprijevozu> listaPutovanjaPojedinogMjeseca = FXCollections.observableArrayList();

    // pomocna lista stringova za comboBox
    private ObservableList<String> listaImena = FXCollections.observableArrayList();

    private ObservableList<Podacioprijevozu> podacizapdf = FXCollections.observableArrayList();

    //za prijevoznoSredstvo
    //tablica
    FXMLLoader loader = new FXMLLoader(Controller.class.getResource("C:\\Users\\David\\Desktop\\EvidencijaPrijevoz\\src\\sample\\sample.fxml"));
    @FXML
    TableView<Podacioprijevozu> tablePrijevoz;
    @FXML
    TableColumn<Podacioprijevozu, LocalDate> DatumStupac;
    @FXML
     TableColumn <Podacioprijevozu, Integer> KmDolazakStupac;
    @FXML
    TableColumn<Podacioprijevozu, Integer> KmOdlazakStupac;
    @FXML
    TableColumn<Podacioprijevozu, String> prijevozStupac;
    @FXML
    TableColumn<Podacioprijevozu, String> potpisStupac;

    //textField za unos podataka o prijevozu - podaciOPrijevozu
    @FXML
    TextField textFieldImePrezimeZaposlenika;
    @FXML
    ComboBox comboBoxZaposlenik;
    @FXML
    DatePicker datePickerDatePrijevoz;
    @FXML
    DatePicker datePickerZaposlenikMjesec;
    @FXML
    TextField textFieldAdresaStanovanja;
    @FXML
    TextField textFieldAdresaRada;
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
    @FXML
    Button Brisanjeredtablice;

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
        datePickerDatePrijevoz.setDisable(true);
        textFieldKModlazakPrijevoz.setDisable(true);
        textFieldKMdolazakPrijevoz.setDisable(true);
        textFieldSredstvoPrijevoz.setDisable(true);
        buttonUnosZaposlenika.setDisable(false);
    }
    /**
     * unos novog zaposlenika, te refresh tabličnog prikaza, koji je za novog zaposlenika prazan
     * @param event
     */
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
            datePickerDatePrijevoz.setDisable(false);
            textFieldKModlazakPrijevoz.setDisable(false);
            textFieldKMdolazakPrijevoz.setDisable(false);
            textFieldSredstvoPrijevoz.setDisable(false);

            // ove četiri linije ispod u ovoj metodi, su samo refresh tablice na prazno nakon unosa novog zaposlenika
            String odabraniZaposlenikString = textFieldImePrezimeZaposlenika.getText();
            Zaposlenik trazeni = listaZaposlenika.stream()
                    .filter(Zaposlenik -> odabraniZaposlenikString.equalsIgnoreCase(Zaposlenik.getImePrezimeZaposlenika())).findAny().orElse(null);
            tablePrijevoz.setItems(trazeni.listaPutovanja);
            buttonUnosZaposlenika.setDisable(true);
        }
    }
    /**
     * Na promjenu selekcije Zaposlenika u comboBoxu, refresha kompletni prikaz putovanja za odabrani mjesec
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
        datePickerFiltriranje();
        unosPutovanja.setDisable(false);
        datePickerDatePrijevoz.setDisable(false);
        textFieldKModlazakPrijevoz.setDisable(false);
        textFieldKMdolazakPrijevoz.setDisable(false);
        textFieldSredstvoPrijevoz.setDisable(false);
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

    //provjera dali je unesen String u Text Field moguće promjeniti u Integer
    public boolean isInteger(String str) {

        if (str == null) {
            return true;
        }
        if (str.isEmpty()) {
            return true;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return true;
            }
            i = 1;
        }
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return true;
            }
        }
        return false;
    }
    /**
     * spremanje podataka o jednom putovanju za odabranog Zaposlenika kad se klikne gumb Unesi
     * */
   @FXML
   private void spremanjeprijevoza(){
       if (isInteger(textFieldKMdolazakPrijevoz.getText()) || isInteger(textFieldKModlazakPrijevoz.getText()) || datePickerDatePrijevoz.getValue().equals(null) ||textFieldKMdolazakPrijevoz.getText().equalsIgnoreCase("") || textFieldKModlazakPrijevoz.getText().equalsIgnoreCase("") || textFieldSredstvoPrijevoz.getText().equalsIgnoreCase("")){
            upozorenje();
       }
       else{
           Podacioprijevozu objektPodaciOPrijevozu = new Podacioprijevozu(datePickerDatePrijevoz.getValue(), Integer.parseInt(textFieldKMdolazakPrijevoz.getText()), Integer.parseInt(textFieldKModlazakPrijevoz.getText()),  textFieldSredstvoPrijevoz.getText());

           // pronalazi zaposlenika u listiZaposlenika čije ime je trenutno u textFieldu
           String odabraniZaposlenikString = textFieldImePrezimeZaposlenika.getText();
           Zaposlenik trazeni = listaZaposlenika.stream()
                   .filter(Zaposlenik -> odabraniZaposlenikString.equalsIgnoreCase(Zaposlenik.getImePrezimeZaposlenika())).findAny().orElse(null);

           // dodaje jedno putovanje za zaposlenika koji se nalazi odabran - trenutni u textFieldu
           trazeni.listaPutovanja.add(objektPodaciOPrijevozu);

           //Dodavanje podataka iz tablice u globalne varijable.
           DatumPDF[BrojPDF]=datePickerDatePrijevoz.getValue().toString();
           KMDPDF[BrojPDF]=textFieldKMdolazakPrijevoz.getText();
           KMOPDF[BrojPDF]=textFieldKModlazakPrijevoz.getText();
           Ptrijevoznosredstvo[BrojPDF]=textFieldSredstvoPrijevoz.getText();
           BrojPDF++;

           // puni tablicu putovanja za odabranog zaposlenika i za odabrani mjesec
            datePickerFiltriranje();
           // brisanje textFielda nakon unosa
           datePickerDatePrijevoz.getEditor().clear();
           textFieldKMdolazakPrijevoz.clear();
           textFieldKModlazakPrijevoz.clear();
           textFieldSredstvoPrijevoz.clear();

           // linije odavde pa do kraja metode su za prikaz poruke nakon dodanog putovanja, poruka samonestaje nakon 1.5 sec
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Putovanje");
           alert.setHeaderText("DODANO PUTOVANJE U "+datePickerDatePrijevoz.getValue().getMonthValue()+". MJESEC "+datePickerDatePrijevoz.getValue().getYear()+".");
           Thread thread = new Thread(() -> {
               try {
                   // Wait for 5 secs
                   Thread.sleep(1000);
                   if (alert.isShowing()) {
                       Platform.runLater(() -> alert.close());
                   }
               } catch (Exception exp) {
                   exp.printStackTrace();
               }
           });
           thread.setDaemon(true);
           thread.start();
           Optional<ButtonType> result = alert.showAndWait();



       }
    }
    // metoda koja rukuje promjenom selekcije datuma u gornjem datePickeru, za odabrani datum vraca mjesec, te za odabrani mjesec pokazuje putovanja zaposlenika, ova metoda poziva se i u ostalim metodama kao refresh prikaza tablice
    @FXML
    private void datePickerFiltriranje (){
        if (textFieldImePrezimeZaposlenika.getText().equalsIgnoreCase("") || textFieldAdresaStanovanja.getText().equalsIgnoreCase("") || textFieldAdresaRada.getText().equalsIgnoreCase("") || listaZaposlenika.isEmpty()){
            upozorenje();
        }
        else {
            listaPutovanjaPojedinogMjeseca.clear();
            String odabraniZaposlenikString = textFieldImePrezimeZaposlenika.getText();
            Zaposlenik trazeni = listaZaposlenika.stream()
                    .filter(Zaposlenik -> odabraniZaposlenikString.equalsIgnoreCase(Zaposlenik.getImePrezimeZaposlenika())).findAny().orElse(null);

            Integer mjesecBroj = datePickerZaposlenikMjesec.getValue().getMonthValue();
            Integer godinaBroj = datePickerZaposlenikMjesec.getValue().getYear();

            for (Podacioprijevozu pod: trazeni.listaPutovanja) {
                if (pod.Datum.getMonthValue() == mjesecBroj && pod.Datum.getYear()== godinaBroj) listaPutovanjaPojedinogMjeseca.add(pod);
            }
            tablePrijevoz.setItems(listaPutovanjaPojedinogMjeseca);

        }
    }
    /**
     * -------------------------------   2. zadatak ---spremanje podataka evidencija kad kliknemo gumb ----------------------------------------------------------------------------------------------
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
    @FXML
    public void Brisanjereda(ActionEvent event) {
        tablePrijevoz.getItems().removeAll(tablePrijevoz.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void fillpdf(){
        Document doc= new Document();
        String filanme = "C:\\Users\\David\\Desktop\\EvidencijaPrijevoz.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filanme));

            doc.open();
            String sveupdfu=new String();
            sveupdfu = "Evidencija prijevoza ";
            Paragraph para= new Paragraph(sveupdfu);
            doc.add(para);
            PdfPTable table1= new PdfPTable(4);
            table1.addCell("Datum");
            table1.addCell("Kilometri dolazak");
            table1.addCell("Kilometri odlazak");
            table1.addCell("Prijevozno sredstvo");
            podacizapdf = tablePrijevoz.getItems();
            for (int i=0; i<BrojPDF;i++){
                    String DatumPDF1 = DatumPDF[i];
                    String KMDPDF1= KMDPDF[i];
                    String KMOPDF1 = KMOPDF[i];
                String Ptrijevoznosredstvo1 = Ptrijevoznosredstvo[i];

                table1.addCell(DatumPDF1);
                table1.addCell(KMDPDF1);
                table1.addCell(KMOPDF1);
                table1.addCell(Ptrijevoznosredstvo1);
            }

            doc.add(table1);

            doc.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }


}
