package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import javafx.fxml.JavaFXBuilderFactory;


public class Controller{
    //za prijevoz
    //tablica
    FXMLLoader loader = new FXMLLoader(Controller.class.getResource("C:\\Users\\David\\Desktop\\EvidencijaPrijevoz\\src\\sample\\sample.fxml"));
    @FXML
    TableView<Podacioprijevozu> tableprijevoz;
    @FXML
    TableColumn<Podacioprijevozu, String> DatumP;
    @FXML
     TableColumn <Podacioprijevozu, String> kmd;
    @FXML
    TableColumn<Podacioprijevozu, String> kmo1;
    @FXML
    TableColumn<Podacioprijevozu, String> prijev;
    @FXML
    TableColumn<Podacioprijevozu, String> pot;
    //unos prijevoz podaci

    @FXML
    TextField DatumPrijevoz;
    @FXML
    TextField KMdolazakPrijevoz;
    @FXML
    TextField KmodlazakPrijevoz;
    @FXML
    TextField SredstvoPrijevoz;
    @FXML
    Button Unos;


    //za evidenciju

    @FXML
    TextField brmjesece;
    @FXML
    TextField brdane;
    @FXML
    TextField bropise;
    @FXML
    Button unosevidencija;
    private ObservableList<Podacioprijevozu> getCharacters(){
        ObservableList<Podacioprijevozu> characters = FXCollections.observableArrayList();
        characters.add(new Podacioprijevozu(DatumPrijevoz.getText(),  KMdolazakPrijevoz.getText(),  KmodlazakPrijevoz.getText(),  SredstvoPrijevoz.getText()));
        return characters;
    }
    //spremanje špodataka prijevoza

    public  void spremanjeprijevoza(){
        Podacioprijevozu PP= new Podacioprijevozu(DatumPrijevoz.getText(),  KMdolazakPrijevoz.getText(),  KmodlazakPrijevoz.getText(),  SredstvoPrijevoz.getText());



        //primjer postavljanje podataka u tablicu
        DatumP.setCellValueFactory(
                new PropertyValueFactory<>("Datum")
        );
        kmd.setCellValueFactory(
                new PropertyValueFactory<>("Brojkmd")
        );
        kmo1.setCellValueFactory(
                new PropertyValueFactory<>("brojkmodlazak")
        );
        prijev.setCellValueFactory(
                new PropertyValueFactory<>("prijevoz")
        );
        pot.setCellValueFactory(
                new PropertyValueFactory<>("potpis")
        );

        tableprijevoz.getItems().add(PP);



    }


    //spremanje podatak evidencija kad kliknemo gumb

    public void pressButton(ActionEvent event){
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
