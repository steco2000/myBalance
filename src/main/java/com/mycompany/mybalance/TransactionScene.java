package com.mycompany.mybalance;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TransactionScene{

    Stage primary;
    Scene transactions, homeWindow;
    TextField amountField;
    TextField descField;
    public static ChoiceBox<String> transactionBillMenu;

    public TransactionScene(Stage primaryStage){
        primary = primaryStage;
    }

    public void setUpT(Stage primaryStage, Scene home) {

        primary = primaryStage;
        homeWindow = home;
        Button homeButton;
        ToolBar upper;

        //ORGANIZZAZIONE SCHERMATA TRANSAZIONI

        TransactionController controller = new TransactionController(primary,homeWindow);

        //creazione homebutton
        Image homeImg = new Image("File:///C://Users//darkd//OneDrive//Desktop//immagini proto//logo.png");
        ImageView view = new ImageView(homeImg);
        view.setFitHeight(100);
        view.setFitWidth(160);
        homeButton = new Button();
        homeButton.setGraphic(view);
        homeButton.setOnAction(e -> primaryStage.setScene(home));

        //creazione toolbar + aggiunta homebutton
        upper = new ToolBar();
        upper.setPrefHeight(100);
        ObservableList ButtList = upper.getItems();
        ButtList.addAll(homeButton);

        //label importo
        Label amount = new Label("Enter the amount of the transaction");

        //textfield importo
        amountField = new TextField();

        //label descrizione
        Label desc = new Label("Enter the description");

        //textfield descrizione
        descField = new TextField();

        //drop menu per i conti da cui vogliamo sia effettuata la transazione
        transactionBillMenu = new ChoiceBox<>();
        transactionBillMenu.getItems().addAll(HomeController.principal.name);
        transactionBillMenu.setValue(HomeController.principal.name);

        Label dMenuLabel = new Label("Select a bill");

        //creazione bottone competa transazione
        Button confButt = new Button("Confirm Transaction");
        confButt.setOnAction(e -> controller.manageTransaction(amountField,descField,transactionBillMenu));     //chiama il metodo manageTransaction che si occupa di effettuare le operazioni di gestione della transazione richiesta

        //SETTAGGIO SCHERMATA TRANSAZIONI
        VBox topMenu = new VBox(30);
        topMenu.getChildren().addAll(upper);

        GridPane centerctn = new GridPane();
        centerctn.setPadding(new Insets(10,10,10,10));
        centerctn.setVgap(8);
        centerctn.setHgap(10);

        GridPane.setConstraints(amount,0,0);
        GridPane.setConstraints(amountField,1,0);
        GridPane.setConstraints(desc,0,1);
        GridPane.setConstraints(descField,1,1);
        GridPane.setConstraints(confButt,1,3);
        GridPane.setConstraints(dMenuLabel, 0, 2);
        GridPane.setConstraints(transactionBillMenu, 1,2);

        BorderPane transScene = new BorderPane();
        transScene.setTop(topMenu);
        transScene.setLeft(centerctn);

        centerctn.getChildren().addAll(amount,amountField,desc,descField,confButt,dMenuLabel,transactionBillMenu);

        transactions = new Scene(transScene, 800, 400);

    }

    public void display(){
        amountField.clear();
        descField.clear();
        primary.setScene(transactions);
    }

}
