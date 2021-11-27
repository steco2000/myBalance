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

public class BillTransfertScene {

    private Stage primary;
    private Scene father;
    Scene userI;
    BillController controller;
    Scene home;
    public static ChoiceBox<String> fromBillMenu;
    public static ChoiceBox<String> targetBillMenu;

    public void setUp(Stage primaryStage, Scene billsS, BillController billC, Scene homeWindow){

        primary = primaryStage;
        father = billsS;
        controller = billC;
        home = homeWindow;

        //creazione homebutton
        Image homeImg = new Image("File:///C://Users//darkd//OneDrive//Desktop//immagini proto//logo.png");
        ImageView view = new ImageView(homeImg);
        view.setFitHeight(100);
        view.setFitWidth(160);
        Button homeButton = new Button();
        homeButton.setGraphic(view);
        homeButton.setOnAction(e -> primary.setScene(home));

        //creazione toolbar + aggiunta homebutton
        ToolBar upper = new ToolBar();
        upper.setPrefHeight(100);
        ObservableList ButtList = upper.getItems();
        ButtList.addAll(homeButton);

        VBox topBar = new VBox();
        topBar.getChildren().addAll(upper);

        TextField amountField = new TextField();

        Label amountLabel = new Label("Insert the amount to transer");

        fromBillMenu = new ChoiceBox<>();
        fromBillMenu.getItems().add(HomeController.principal.name);
        fromBillMenu.setValue(HomeController.principal.name);

        Label fromLabel = new Label("Select the bill to transfer from");

        targetBillMenu = new ChoiceBox<>();
        targetBillMenu.getItems().add(HomeController.principal.name);
        targetBillMenu.setValue(HomeController.principal.name);

        Button confButt = new Button("Confirm");
        confButt.setOnAction(e -> {
            BillController.transferFunds(fromBillMenu,targetBillMenu,amountField,father);
        });

        Button back = new Button("Cancel");
        back.setOnAction(e -> primary.setScene(father));

        Label targetLabel = new Label("Select the bill to transfer to");

        GridPane items = new GridPane();
        items.setPadding(new Insets(10, 10, 10, 10));
        items.setVgap(8);
        items.setHgap(10);

        GridPane.setConstraints(amountLabel,0,0);
        GridPane.setConstraints(amountField,1,0);
        GridPane.setConstraints(fromLabel, 0, 1);
        GridPane.setConstraints(fromBillMenu, 1, 1);
        GridPane.setConstraints(targetLabel, 0, 2);
        GridPane.setConstraints(targetBillMenu, 1, 2);
        GridPane.setConstraints(back,0,3);
        GridPane.setConstraints(confButt,1,3);

        items.getChildren().addAll(amountLabel,amountField,fromLabel,fromBillMenu,targetLabel,targetBillMenu,confButt,back);

        VBox center = new VBox();
        center.getChildren().add(items);

        BorderPane layout = new BorderPane();
        layout.setCenter(center);
        layout.setTop(upper);

        userI = new Scene(layout,800,400);

    }

    public void display(){
        primary.setScene(userI);
    }

}
