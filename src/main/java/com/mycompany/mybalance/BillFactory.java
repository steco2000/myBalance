package com.mycompany.mybalance;

import javafx.stage.Stage;

public class BillFactory {

    public static BillEntity createBill(double amountToTransfer, BillEntity fromBill, Stage primaryStage){
        return new BillEntity(amountToTransfer, fromBill, primaryStage);
    }

}
