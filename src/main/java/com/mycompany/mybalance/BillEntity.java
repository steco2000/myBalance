package com.mycompany.mybalance;

import javafx.stage.Stage;

public class BillEntity extends AbstractBill{

    public String name;
    public int index;

    public BillEntity(double amountToTransfer, BillEntity fromBill) {
        super(amountToTransfer, fromBill);
    }


    public void submitTransaction(TransactionEntity trTodo){
        System.out.println("Transaction specifies - type : "+trTodo.type+", amount : "+trTodo.amount+", bill : "+trTodo.billRef.name);
        if (trTodo.type.equals("Incoming")){
            total += trTodo.amount;
            HomeScene.avbalance += trTodo.amount;
        }
        else {
            total -= trTodo.amount;
            HomeScene.avbalance -= trTodo.amount;
        }
        String balanceMenuSelecetedItem = HomeScene.billBalanceMenu.getValue();
        if(balanceMenuSelecetedItem.equals("Total")){
            HomeScene.abview.setText("$ " + HomeScene.avbalance);
        }else{
            BillEntity selected = BillController.searchBill(balanceMenuSelecetedItem);
            HomeScene.abview.setText("$ " + selected.total);
        }
        System.out.println("Total available balance after transaction : $ "+HomeScene.avbalance);
        (BillsScene.labelarray[index]).setText(name+" : $"+total);
    }

    public void withdrawFunds(double amount, BillEntity fromBill){
        fromBill.total -= amount;
        this.total += amount;
        //todo : altre variabili statiche
        BillsScene.labelarray[fromBill.index].setText(fromBill.name+": $ "+fromBill.total);
        BillsScene.labelarray[this.index].setText(this.name+": $ "+this.total);
    }



}
