package com.mycompany.mybalance;

import javafx.application.Application;
import javafx.stage.Stage;

public class myBalance extends Application {

    boolean loginEnabled = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        if(loginEnabled) {
            LoginScene login = new LoginScene(primaryStage);
            login.setUp();
            login.display();
        }else {
            primaryStage.setTitle("myBalance");
            primaryStage.setResizable(false);    //TODO : blocco dimensione schermata, da sostituire in seguito
            HomeScene home = new HomeScene(primaryStage);
            home.setUp();
            home.display();
            primaryStage.show();
        }

    }
}


