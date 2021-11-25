package com.mycompany.mybalance;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class BillController {

    private final Stage primary;
    private double amountMem;
    private double eventualTransferAmount;
    public static BillEntity contoProva;  //TODO : Conto di prova, alla creazione del conto ci sarò una nuova entry nel DB, la crezione di questa sarà gestita da un metodo controllore
    public static Label cProvaLabel;

    public static BillEntity[] billArray = new BillEntity[5];      //TODO : PER FAR FUNZIONARE IL SISTEMA DEI CONTI HO INSTANZIATO UN ARRAY A DIM. FISSA, MA DEVE ESSERE VARIABILE
    public static int totalBills = 0;

    public BillController(Stage primaryStage,Scene billsUI){
        primary = primaryStage;
        bills = billsUI;
    }

    public static void addBillarray(BillEntity newBill, Label newBillLabel){
        billArray[totalBills] = newBill;
        //todo : accesso a var. statica
        BillsScene.labelarray[totalBills] = newBillLabel;
        totalBills++;
    }

    //todo : modifica var. globale amountMem in metodo secondario, riformulare
    private boolean isDouble(TextField input){
        String message = input.getText();
        try{
            double amount = Double.parseDouble(message);
            message = message.replace(",",".");
            System.out.println("Amount is : " + amount);
            amountMem = amount;
            return true;
        }catch(NumberFormatException e){
            if(message == ""){
                AlertBox.display("Error","Error : you can't have an empty bill!");
            }else{
                AlertBox.display("Error", "Error : " + message + " is not a number!");
            }
            return false;
        }
    }

    //TODO : COLLEGA DB
    public void manageCreateBill(TextField amountField, TextField descField, ChoiceBox<String> fromBillSelectionMenu) {
        boolean check = false;
        boolean result = ConfirmBox.display("Confirming Creation", "Are you sure?", false);
        System.out.println(result);
        if (result) check = isDouble(amountField);
        if (result && check) {
            String fromBillName = (String) fromBillSelectionMenu.getValue();
            BillEntity fromBill = searchBill(fromBillName);
            if (amountMem <= fromBill.total) {
                contoProva = new BillEntity(amountMem, fromBill);
                contoProva.name = descField.getText();
                contoProva.index = totalBills;

                //todo : accessi a var. statiche
                TransactionScene.transactionBillMenu.getItems().add(contoProva.name);
                fromBillSelectionMenu.getItems().add(contoProva.name);
                BillTransfertScene.fromBillMenu.getItems().add(contoProva.name);
                BillTransfertScene.targetBillMenu.getItems().add(contoProva.name);
                HomeScene.billBalanceMenu.getItems().add(contoProva.name);

                cProvaLabel = new Label(descField.getText() + " : $" + amountMem);
                cProvaLabel.setFont(new Font("Arial", 30));
                //todo: metodo statico
                BillController.addBillarray(contoProva, cProvaLabel);
                BillsScene.centerC.getChildren().addAll(cProvaLabel);
                System.out.println("AmountMem : " + amountMem + ", total : " + fromBill.total);
                BillsScene.labelarray[fromBill.index].setText(fromBillName +" : $ " + fromBill.total);
                //todo: var. statica
                primary.setScene(BillsScene.bills);
            }else {
                //todo : var statica
                AlertBox.display("Error :", "Error : insufficent funds in " + HomeController.principal.name);
            }
        }
    }

    //todo : modifica var. globale eventualtrasferamount in metodo secondario, riformulare
    private boolean isDoubleTransfer(TextField input){
        String message = input.getText();
        try{
            double amount = Double.parseDouble(message);
            message = message.replace(",",".");
            System.out.println("Amount is : " + amount);
            eventualTransferAmount = amount;
            return true;
        }catch(NumberFormatException e){
            if(message == ""){
                AlertBox.display("Error","Error : you can't have an empty transfert!");
            }else{
                AlertBox.display("Error", "Error : " + message + " is not a number!");
            }
            return false;
        }
    }

    public void transferFunds(ChoiceBox<String> fromMenu, ChoiceBox<String> targetMenu, TextField amountField, Scene father) {
        String fromName = fromMenu.getValue();
        String targetName = targetMenu.getValue();
        BillEntity fromBill = searchBill(fromName);
        BillEntity targetBill = searchBill(targetName);
        boolean check = isDoubleTransfer(amountField);
        boolean result = ConfirmBox.display("Fund Transfer", "Are you sure?",false);
        if (result && check) {
            System.out.println("Trasfering funds - amount to transfer : $"+eventualTransferAmount+", from a bill with : $"+fromBill.total);
            if(eventualTransferAmount <= fromBill.total) {
                System.out.println("Starting transfert");
                targetBill.withdrawFunds(eventualTransferAmount,fromBill);
                //todo : var. statiche
                BillsScene.labelarray[fromBill.index].setText(fromBill.name+" : $ "+fromBill.total);
                BillsScene.labelarray[targetBill.index].setText(targetBill.name+" : $ "+targetBill.total);
                System.out.println("Trasfering funds - from : $ "+fromBill.total+", target : $ "+targetBill.total);
                String homeSelected = HomeScene.billBalanceMenu.getValue();
                if(homeSelected.equals(fromName)){
                    HomeScene.abview.setText("$ "+fromBill.total);
                }else if(homeSelected.equals(targetName)){
                    HomeScene.abview.setText("$ "+targetBill.total);
                }
                primary.setScene(father);
            }else{
                AlertBox.display("Error","Not enough funds in the bill selected!");
            }
        }
    }

    public static BillEntity searchBill(String toSearch){
        int n = totalBills;
        System.out.println("Total bills available in 'searchBill' : "+n);
        for(int i=0; i<n; i++){
            System.out.println("Scanning bill array - "+BillController.billArray[i].name);
            if(Objects.equals(billArray[i].name, toSearch)){
                return billArray[i];
            }
        }
        return null;
    }

}
