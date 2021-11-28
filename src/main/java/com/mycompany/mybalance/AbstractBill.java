package com.mycompany.mybalance;

import javafx.stage.Stage;

public abstract class AbstractBill {

    public Stage primary;
    public double total;

    public AbstractBill(double amountToTransfer, BillEntity fromBill) {

        if(fromBill != null){
            total = amountToTransfer;
            if (fromBill.total >= amountToTransfer) {
                fromBill.total -= amountToTransfer;
            }else{
                AlertBox.display("Error :", "Error : insufficent funds in "+fromBill.name);
            }
        }else{
            total = amountToTransfer;
        }

    }

    public abstract void submitTransaction(TransactionEntity trTodo);
    public abstract void withdrawFunds(double amount, BillEntity fromBill);

}
