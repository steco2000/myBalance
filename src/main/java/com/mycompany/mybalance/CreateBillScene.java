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

public class CreateBillScene {

    BillsScene billsFather;
    Stage primary;
    Scene createBS;
    public TextField amountField;
    public TextField descField;
    public static ChoiceBox<String> fromBillSelectionMenu;

    public void setUpCB(Stage primaryStage, Scene home, BillsScene billsA) {

        primary = primaryStage;
        billsFather = billsA;
        Button homeButton;
        ToolBar upper;

        //ORGANIZZAZIONE SCHERMATA TRANSAZIONI

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
        Label amount = new Label("Enter the amount of the Bill");

        //textfield importo
        amountField = new TextField();

        //label descrizione
        Label desc = new Label("Enter Name");

        //textfield descrizione
        descField = new TextField();

        //creazione bottone competa transazione
        Button confButt = new Button("Create Bill");

        //bottone cancel
        Button goBack = new Button("Cancel");
        goBack.setOnAction(e -> primary.setScene(BillsScene.bills));

        fromBillSelectionMenu = new ChoiceBox<>();
        fromBillSelectionMenu.getItems().add(HomeController.principal.name);
        fromBillSelectionMenu.setValue(HomeController.principal.name);

        Label billMenuLabel = new Label("Select a bill to transfer funds");

        //SETTAGGIO SCHERMATA TRANSAZIONI
        VBox topMenu = new VBox(30);
        topMenu.getChildren().addAll(upper);

        GridPane centerctn = new GridPane();
        centerctn.setPadding(new Insets(10, 10, 10, 10));
        centerctn.setVgap(8);
        centerctn.setHgap(10);

        GridPane.setConstraints(amount, 0, 0);
        GridPane.setConstraints(amountField, 1, 0);
        GridPane.setConstraints(desc, 0, 1);
        GridPane.setConstraints(descField, 1, 1);
        GridPane.setConstraints(fromBillSelectionMenu,1,2);
        GridPane.setConstraints(billMenuLabel,0,2);
        GridPane.setConstraints(confButt, 1, 3);
        GridPane.setConstraints(goBack, 0, 3);

        BorderPane transScene = new BorderPane();
        transScene.setTop(topMenu);
        transScene.setLeft(centerctn);

        centerctn.getChildren().addAll(amount, amountField, desc, descField, fromBillSelectionMenu, billMenuLabel, confButt, goBack);

        createBS = new Scene(transScene, 800, 400);

        confButt.setOnAction(e -> BillController.manageCreateBill(amountField,descField,fromBillSelectionMenu));
    }

    public void display(){
        amountField.clear();
        descField.clear();
        primary.setScene(createBS);
    }

}
