/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybakery;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mybakery.Modul.Penghitung;
import mybakery.Modul.Roti;

/**
 *
 * @author NizomSidiq
 */
public class KasirController implements Initializable {
    
    public Roti[] roti = new Roti[10];
    Penghitung calc = new Penghitung();
    
    @FXML
    private Label lbNo, lbNama, lbHarga, lbStok, lbLaku, lbOmset, lbTotStok, lbTotLaku, lbTotOmset;
    
    @FXML 
    private Button btnJual0, btnKembali0, btnJual1, btnKembali1, btnJual2, btnKembali2, btnJual3, btnKembali3, btnJual4, btnKembali4, btnJual5, btnKembali5, btnJual6, btnKembali6, btnJual7, btnKembali7, btnJual8, btnKembali8, btnJual9, btnKembali9;
    
    @FXML
    private TextField txFNoTmbhStok, txFQtyTmbhStok, txFNoTerjual, txFQtyTerjual;
    
    @FXML
    private void eTambahStok(ActionEvent e)
    {
        int no = Integer.parseInt(txFNoTmbhStok.getText());
        int qty = Integer.parseInt(txFQtyTmbhStok.getText());
        if (no > 0 && no < 11 ) {
            roti[no - 1].tmbhStok(qty);
            System.out.println(String.valueOf(qty) + " stok " + roti[no - 1].bacaNama() +  " telah ditambakan");
        }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Nomor yang anda masukkan tidak ditemukan \n"
                    + "Silahkan pilih no. 1 - 10" );
            alert.showAndWait();
        }
        tampil();
        txFNoTmbhStok.clear();
        txFQtyTmbhStok.clear();
    }
    
    @FXML
    private void eJualRoti(ActionEvent e)
    {
        int no = Integer.parseInt(txFNoTerjual.getText());
        int qty = Integer.parseInt(txFQtyTerjual.getText());
        if (no > 0 && no < 11 ) {
            roti[no - 1].terjual(qty);
            System.out.println(String.valueOf(qty) + " " + roti[no - 1].bacaNama() + " telah terjual");
        }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Nomor yang anda masukkan tidak ditemukan \n"
                    + "Silahkan pilih no. 1 - 10" );
            alert.showAndWait();
        }
        tampil();
        txFNoTerjual.clear();
        txFQtyTerjual.clear();
    }
    
    private void terjual(Roti roti, int laku)
    {
        roti.terjual(laku);
        System.out.println(String.valueOf(laku) + " " + roti.bacaNama() + " telah terjual");
        tampil();
    }
    
     private void kembali(Roti roti, int laku)
    {
        roti.kembali(laku);
        System.out.println(String.valueOf(laku) + " " + roti.bacaNama() +  " telah dikembalikan");
        tampil();
    }
     
    private void tampil() {
        String dataNo, dataNama, dataHarga, dataStok, dataLaku, dataOmset, dataTotStok, dataTotLaku, dataTotOmset;
        dataNo = dataNama = dataHarga = dataStok = dataLaku = dataOmset = dataTotStok = dataTotLaku = dataTotOmset = "";
        
        //mengisi data
        for (int i = 0; i < roti.length; i++) {
            dataNo += String.valueOf(i + 1) + "\n";
            dataNama += roti[i].bacaNama() + "\n";
            dataHarga += String.valueOf(roti[i].bacaHarga()) + "\n";
            dataStok += String.valueOf(roti[i].bacaStok()) + "\n";
            dataLaku += String.valueOf(roti[i].bacaLaku()) + "\n";
            dataOmset += String.valueOf(roti[i].bacaOmset()) + "\n";
        }
        dataTotStok = String.valueOf(calc.totalStok(roti));
        dataTotLaku = String.valueOf(calc.totalLaku(roti));
        dataTotOmset = String.valueOf(calc.totalOmset(roti));
        
        //menampilkan di GUI
        this.lbNo.setText(dataNo);
        this.lbNama.setText(dataNama);
        this.lbHarga.setText(dataHarga);
        this.lbStok.setText(dataStok);
        this.lbLaku.setText(dataLaku);
        this.lbOmset.setText(dataOmset);
        this.lbTotStok.setText(dataTotStok);
        this.lbTotLaku.setText(dataTotLaku);
        this.lbTotOmset.setText(dataTotOmset);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roti[0] = new Roti("Sus Susu", 20, 3000);
        roti[1] = new Roti("Sus Keju", 20, 4000);
        roti[2] = new Roti("Sus Panjang", 20, 6000);
        roti[3] = new Roti("Donat Manis", 20, 3000);
        roti[4] = new Roti("Donat Coklat", 20, 4000);
        roti[5] = new Roti("Donat Strawberry", 20, 4000);
        roti[6] = new Roti("Sisir Manis", 20, 4000);
        roti[7] = new Roti("Sisir Coklat", 20, 5000);
        roti[8] = new Roti("Sisir Strawberry", 20, 5000);
        roti[9] = new Roti("Tawar Putih", 10, 9000);
    
        btnJual0.setOnAction(e -> terjual(roti[0], 1));
        btnJual1.setOnAction(e -> terjual(roti[1], 1));
        btnJual2.setOnAction(e -> terjual(roti[2], 1));
        btnJual3.setOnAction(e -> terjual(roti[3], 1));
        btnJual4.setOnAction(e -> terjual(roti[4], 1));
        btnJual5.setOnAction(e -> terjual(roti[5], 1));
        btnJual6.setOnAction(e -> terjual(roti[6], 1));
        btnJual7.setOnAction(e -> terjual(roti[7], 1));
        btnJual8.setOnAction(e -> terjual(roti[8], 1));
        btnJual9.setOnAction(e -> terjual(roti[9], 1));
        btnKembali0.setOnAction(e -> kembali(roti[0], 1));
        btnKembali1.setOnAction(e -> kembali(roti[1], 1));
        btnKembali2.setOnAction(e -> kembali(roti[2], 1));
        btnKembali3.setOnAction(e -> kembali(roti[3], 1));
        btnKembali4.setOnAction(e -> kembali(roti[4], 1));
        btnKembali5.setOnAction(e -> kembali(roti[5], 1));
        btnKembali6.setOnAction(e -> kembali(roti[6], 1));
        btnKembali7.setOnAction(e -> kembali(roti[7], 1));
        btnKembali8.setOnAction(e -> kembali(roti[8], 1));
        btnKembali9.setOnAction(e -> kembali(roti[9], 1));
        
        tampil();
        System.out.println("Program Started!");
    }    
    
}
