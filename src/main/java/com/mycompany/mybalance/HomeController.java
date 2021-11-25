package com.mycompany.mybalance;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HomeController {

    public static BillEntity principal;

    public void createPrincipalBill(){
        principal = new BillEntity(2000,null);
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
