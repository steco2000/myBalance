package com.mycompany.myBalance;

public class BillEntity {

    public double total;
    public String name;
    public int index;

    public BillEntity(double amountToTransfer, BillEntity fromBill){

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

    public void submitTransaction(double amount){
        total += amount;
        HomeScene.avbalance += amount;
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
