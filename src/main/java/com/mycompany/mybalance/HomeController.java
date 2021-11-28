package com.mycompany.mybalance;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeController {

    public static BillEntity principal;
    private final Stage primary;

    public HomeController(Stage primaryStage){
        primary = primaryStage;
    }

    public void createPrincipalBill(){
        principal = BillFactory.createBill(2000,null);
        principal.name = "Principal Bill";
        Label principalLabel = new Label("Principal Bill : $ "+principal.total);
        principalLabel.setFont(new Font("Arial",30));
        //todo : metodo statico, cercare di riformulare
        BillController.addBillarray(principal,principalLabel);
        HomeScene.avbalance += principal.total;
        HomeScene.abview.setText("$ "+HomeScene.avbalance);
    }

    public void manageBillBalanceMenu(String newValue){
        //todo : vedi il conto selezionato e stampa il totale nella label
        if(!newValue.equals("Total")) {
            BillEntity selectedBill = BillController.searchBill(newValue);
            HomeScene.abview.setText("$ "+selectedBill.total);
        }else{
            HomeScene.abview.setText("$ "+HomeScene.avbalance);
        }
    }

}
