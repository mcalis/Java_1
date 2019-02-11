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
    FXMLLoader loader = new FXMLLoader(Controller.class.getResource("C:\\Users\\David\\IdeaProjects\\EvidencijaPrijevoz\\src\\sample\\sample.fxml"));
    @FXML
    TableView<Podacioprijevozu> tableprijevoz;
    @FXML
    TableColumn<Podacioprijevozu, String> Datum;
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
    ObservableList<Podacioprijevozu> characters = FXCollections.observableArrayList();
    private ObservableList<Podacioprijevozu> getCharacters(){


        return characters;
    }
    //spremanje podataka prijevoza

    public  void spremanjeprijevoza(){
      //  Podacioprijevozu PP= new Podacioprijevozu(DatumPrijevoz.getText(),  KMdolazakPrijevoz.getText(),  KmodlazakPrijevoz.getText(),  SredstvoPrijevoz.getText());
        characters.add(new Podacioprijevozu(DatumPrijevoz.getText(),  KMdolazakPrijevoz.getText(),  KmodlazakPrijevoz.getText(),  SredstvoPrijevoz.getText()));

        tableprijevoz.setItems(getCharacters());
        //primjer postavljanje podataka u tablicu
        Datum.setCellValueFactory(
                new PropertyValueFactory<>("Datum1")
        );
        kmd.setCellValueFactory(
                new PropertyValueFactory<>("Brojkmd1")
        );
        kmo1.setCellValueFactory(
                new PropertyValueFactory<>("brojkmodlazak1")
        );
        prijev.setCellValueFactory(
                new PropertyValueFactory<>("prijevoz1")
        );
        pot.setCellValueFactory(
                new PropertyValueFactory<>("potpis")
        );

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
