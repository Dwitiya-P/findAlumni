/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybakery;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mybakery.Modul.Lemari;

/**
 * FXML Controller class
 *
 * @author NizomSidiq
 */
public class GudangController implements Initializable {

    @FXML 
    private ComboBox cbLemari;
    
    @FXML
    private Label lbDataNo, lbDataRak, lbDataStok;
    
    @FXML
    private TextField tfNoRak1, tfQty1,
                      tfNoRak2, tfGantiNama;
    
    public Lemari[] lemari = new Lemari[3];
    private int jmlLemari = lemari.length;
    private int pos;
    
    private void lihat(String str){
        int pil = str == "Lemari 1" ? 0 :
                  str == "Lemari 2" ? 1 :
                  str == "Lemari 3" ? 2 : 
                  -1;
        this.pos = pil;
        tampil(pil);
    }
    
    @FXML
    public void ubahNamaAction(ActionEvent e){
        int rak = Integer.parseInt(tfNoRak2.getText());
        String namaBaru = tfGantiNama.getText();
        
        lemari[this.pos].namaiRak(rak, namaBaru);
        tampil(this.pos);
        tfNoRak2.clear();
        tfGantiNama.clear();
    }
    
    @FXML
    public void tambahStokAction(ActionEvent e){
        int rak = Integer.parseInt(tfNoRak1.getText());
        int qty = Integer.parseInt(tfQty1.getText());
        
        lemari[this.pos].tambahStok(rak, qty);
        tampil(this.pos);
    }
 
    @FXML
    public void kurangiStokAction(ActionEvent e){
        int rak = Integer.parseInt(tfNoRak1.getText());
        int qty = Integer.parseInt(tfQty1.getText());
        
        lemari[this.pos].kurangiStok(rak, qty);
        tampil(this.pos);
    }
    
    private void tampil(int pil){
        String dataNo, dataNama, dataStok;
        dataNo = dataNama = dataStok = "";
        
        for (int i = 0; i < lemari[pil].bacaJmlRak(); i++) {
            dataNo += String.valueOf(i + 1) + "\n";
            dataNama += lemari[pil].bacaRak(i)+ "\n";
            dataStok += String.valueOf(lemari[pil].bacaStokRak(i)) + "\n";
        }
        lbDataNo.setText(dataNo);
        lbDataRak.setText(dataNama);
        lbDataStok.setText(dataStok);    
    }
    
    @FXML
    public void bukaKasirAction(ActionEvent e){
        Parent root;
        MyBakery b = new MyBakery();
        try {
            root = FXMLLoader.load(getClass().getResource("Kasir.fxml"));
            Stage stage = new Stage();
            stage.setTitle("NeneQ's Bakery - Kasir");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lemari[0] = new Lemari(3);
        lemari[1] = new Lemari(3);
        lemari[2] = new Lemari(3);
        
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Lemari 1",
                        "Lemari 2",
                        "Lemari 3"
                );
        cbLemari.setItems(options);
        cbLemari.setOnAction(e -> lihat(cbLemari.getValue().toString()));

        lemari[0].namaiRak(1, "Donat Coklat");
        lemari[0].namaiRak(2, "Donat Susu");
        lemari[0].namaiRak(3, "Donat Stroberi");
        lemari[1].namaiRak(1, "Sus Coklat");
        lemari[1].namaiRak(2, "Sus Susu");
        lemari[1].namaiRak(3, "Sus Stroberi");
        lemari[2].namaiRak(1, "Tawar Coklat");
        lemari[2].namaiRak(2, "Tawar Susu");
        lemari[2].namaiRak(3, "Tawar Stroberi");
    }    
    
}
