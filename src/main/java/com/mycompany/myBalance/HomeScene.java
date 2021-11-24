package com.mycompany.myBalance;

//TODO : IN TUTTO IL SISTEMA CERCARE DI RIDURRE AL MINIMO CHIAMATE STATIC PER RIDURRE IL COUPLING

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeScene {

    private final Stage primaryStage;
    static Stage homeWindow;
    Scene home;
    Button homeButton, menu1, menu2, menu4, menu5;
    ToolBar upper;
    public static double avbalance;
    public static Label abview;
    public static ChoiceBox<String> billBalanceMenu;

    public HomeScene(Stage primary){
        primaryStage = primary;
    }

    public void setUp(){

        homeWindow = primaryStage;

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //dichiarazione schermata transazioni
        TransactionScene transactions = new TransactionScene(primaryStage);

        //dichiarazione schermata conti
        BillsScene bills = new BillsScene();

        HomeController controller = new HomeController();

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

        //creazione e posizionamento etichetta saldo disponibile
        abview = new Label();
        //abview.setTranslateX(400);
        //abview.setTranslateY(20);
        abview.setFont(new Font("Arial",30));
        //abview.setTextFill(Color.web("#00B300"));     //TODO : L'importo totale nella schermata home deve avere colore verde se positivo, rosso se negativo

        controller.createPrincipalBill();

        billBalanceMenu = new ChoiceBox<>();
        billBalanceMenu.getItems().addAll("Total",HomeController.principal.name);
        billBalanceMenu.setValue("Total");

        billBalanceMenu.getSelectionModel().selectedItemProperty().addListener( (v,old,newValue) -> controller.manageBillBalanceMenu(newValue));

        HBox centercnt = new HBox();
        centercnt.getChildren().addAll(billBalanceMenu,abview);
        centercnt.setAlignment(Pos.TOP_CENTER);
        centercnt.setSpacing(20);
        centercnt.setTranslateY(20);

        //pulsanti menu a sinistra
        menu1 = new Button("Add Transaction");      //Lambdas : button.setOnAction(e -> {*evento da settare (se istruzione singola anche senza graffe)*})
        menu1.setPrefSize(160,100);
        menu1.setOnAction(e -> transactions.display());

        menu2 = new Button("Bills");
        menu2.setPrefSize(160,100);
        menu2.setOnAction(e -> bills.display());

        menu4 = new Button("Browse Financings");
        menu4.setPrefSize(160,100);

        menu5 = new Button("About");
        menu5.setPrefSize(160,100);
        menu5.setOnAction( e -> AlertBox.display("About","myBalance - all rights reserved - Version : 1.0.0"));

        //SETTAGGIO SCHERMATA HOME

        VBox topmenu = new VBox();
        topmenu.getChildren().addAll(upper);

        VBox leftmenu = new VBox();
        leftmenu.getChildren().addAll(menu1,menu2,menu4,menu5);

        BorderPane root = new BorderPane();
        root.setTop(topmenu);
        root.setLeft(leftmenu);
        root.setCenter(centercnt);

        home = new Scene(root,800,400);

        //set up scherm. transazioni, passo il main stage e la scena home per poterle risettare anche da lì
        transactions.setUpT(primaryStage,home);

        //set up scherm. conti
        bills.setUpB(primaryStage,home);

    }

    public void display(){
        primaryStage.setScene(home);
    }

    //METODO GESTIONE CHIUSURA
    private void closeProgram(){
        boolean result = ConfirmBox.display("Exit","Are you sure to exit?",true);
        if(result) {
            homeWindow.close();
        }
    }

}
