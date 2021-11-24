package com.mycompany.myBalance;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message, boolean notNecessary){

        Stage window = new Stage();
        window.setResizable(false); //blocca la dimensione del popup

        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane layout = new BorderPane();
        VBox text = new VBox(20);
        HBox butts = new HBox(20);

        Label label = new Label(message);

        //yes button
        Button yesButt = new Button("Yes");
        yesButt.setPrefSize(100,20);
        yesButt.setOnAction(e -> {
            answer = true;
            window.close();
        });

        //no button
        Button noButt = new Button("No");
        noButt.setPrefSize(100,20);
        noButt.setOnAction(e -> {
            answer = false;
            window.close();
        });

        //checkbox per specificare di non voler mostrare più il popup (esiste solo se il popup non è necessario, se possibile inserirlo nell'if)
        CheckBox noBox = new CheckBox("Don't show again");

        text.getChildren().addAll(label);
        if(notNecessary){
            //noBox.setSelected(true);     //lo inizializza selezionato
            text.getChildren().add(noBox);
        }

        butts.getChildren().addAll(yesButt, noButt);

        //accentra testo e bottoni
        text.setAlignment(Pos.CENTER);
        butts.setAlignment(Pos.CENTER);

        //alzali leggermente
        text.setTranslateY(-20);
        butts.setTranslateY(-40);

        layout.setCenter(text);
        layout.setBottom(butts);

        Scene scene = new Scene(layout,400,200);
        window.setScene(scene);
        window.showAndWait();

        if (notNecessary) System.out.println(noBox.isSelected());     //stampa true se è selezionato
        return answer;

    }

}
