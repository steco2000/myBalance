package com.mycompany.mybalance;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BillsScene {

    public static VBox centerC;
    Button homeButton;
    ToolBar upper;
    Stage primary;
    Scene homeWindow;
    public static Scene bills;
    public static Label[] labelarray = new Label[5];    //TODO : VA NEL CONTROLLORE

    public void setUpB(Stage primaryStage, Scene home){

        primary = primaryStage;
        homeWindow = home;

        CreateBillScene createBillScene = new CreateBillScene();
        BillTransfertScene trScene = new BillTransfertScene();

        //creazione homebutton
        Image homeImg = new Image("File:///C://Users//darkd//OneDrive//Desktop//immagini proto//logo.png");
        ImageView view = new ImageView(homeImg);
        view.setFitHeight(100);
        view.setFitWidth(160);
        homeButton = new Button();
        homeButton.setGraphic(view);
        homeButton.setOnAction(e -> primary.setScene(home));

        //creazione toolbar + aggiunta homebutton
        upper = new ToolBar();
        upper.setPrefHeight(100);
        ObservableList ButtList = upper.getItems();
        ButtList.addAll(homeButton);

        //TODO : il centerC dovrà essere una griglia contentente i conti e i relativi importi
        centerC = new VBox(30);
        centerC.setAlignment(Pos.TOP_CENTER);
        centerC.getChildren().addAll(labelarray[0]);

        //Pulsante crea nuovo conto
        VBox leftMenu = new VBox();
        Button createBill = new Button("Create a new Bill");
        createBill.setPrefSize(160,100);
        createBill.setOnAction(e -> createBillScene.display());

        Button transferButton = new Button("Transfer funds");
        transferButton.setPrefSize(160,100);
        transferButton.setOnAction(e -> trScene.display());

        leftMenu.getChildren().addAll(createBill,transferButton);

        VBox topBar = new VBox();
        topBar.getChildren().addAll(upper);

        BorderPane layout = new BorderPane();
        layout.setTop(topBar);
        layout.setLeft(leftMenu);
        layout.setCenter(centerC);

        bills = new Scene(layout, 800, 400);

        BillController controller = new BillController(primary);
        createBillScene.setUpCB(primary,home,this);
        trScene.setUp(primary,bills,controller,home);

    }

    public void display(){
        primary.setScene(bills);
    }

}
