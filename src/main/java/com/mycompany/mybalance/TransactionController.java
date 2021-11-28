package com.mycompany.mybalance;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionController {

    private final Stage primary;
    private double amountMem;
    private final Scene homeWindow;

    public TransactionController(Stage primaryStage, Scene home){
        primary = primaryStage;
        homeWindow = home;
    }

    //metodo evento gestione transazioni (ciò che succede quando si preme confirm transaction)
    public void manageTransaction(TextField amountField, TextField descField, ChoiceBox<String> transactionBillMenu,String type){
        BillEntity frombill;
        boolean check = false;
        boolean result = ConfirmBox.display("Confirming Transaction", "Are you sure?",false);
        System.out.println(result);
        if (result) check = isDouble(amountField);
        if(result && check) {
            if(type.equals("Incoming")) {
                System.out.println("Description : " + descField.getText());
                String billName = transactionBillMenu.getValue();
                System.out.println("Selected bill : " + billName);
                //todo : metodo statico, cercare di riformulare
                frombill = BillController.searchBill(billName);
                System.out.println("Bill sorted in array : " + frombill.name);
                frombill.submitIncoming(amountMem);
                primary.setScene(homeWindow);
            }else{
                System.out.println("Description : " + descField.getText());
                String billName = transactionBillMenu.getValue();
                System.out.println("Selected bill : " + billName);
                //todo : metodo statico, cercare di riformulare
                frombill = BillController.searchBill(billName);
                System.out.println("Bill sorted in array : " + frombill.name);
                frombill.submitExpense(amountMem);
                primary.setScene(homeWindow);
            }
        }
    }

    //todo : assegrazione globale amountmem in metodo secondario, riformulare
    public boolean isDouble(TextField input){
        String message = input.getText();
        try{
            message = message.replace(",",".");
            double amount = Double.parseDouble(message);
            if (amount <= 0){
                AlertBox.display("Error","Transaction import can't be zero or negative");
                return false;
            }
            System.out.println("Amount is : " + amount);
            amountMem = amount;
            return true;
        }catch(NumberFormatException e){
            if(message.equals("")){
                AlertBox.display("Error","Error : you can't submit an empty transaction!");
            }else{
                AlertBox.display("Error", "Error : " + message + " is not a number!");
            }
            return false;
        }
    }

}
