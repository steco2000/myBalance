package com.mycompany.myBalance;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message){

        Stage window = new Stage();
        window.setResizable(false);
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);       //blocca le azioni dell'utente al solo box

        VBox root = new VBox(50);

        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());
        closeButton.setPrefSize(100,20);
        Label label = new Label(message);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(root,400,200);
        window.setScene(scene);
        window.showAndWait();

    }

}
