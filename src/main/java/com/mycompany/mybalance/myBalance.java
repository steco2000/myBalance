package com.mycompany.mybalance;

import javafx.application.Application;
import javafx.stage.Stage;

public class myBalance extends Application {

    boolean loginEnabled = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        if (loginEnabled) {
            LoginScene login = new LoginScene(primaryStage);
            login.setUp();
            login.display();
        }else{
            GUIBuilder builder = new GUIBuilder(primaryStage);
            builder.launch();
        }

    }

}


