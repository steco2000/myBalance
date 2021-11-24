package com.mycompany.myBalance;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene {

    Stage primary;
    Scene lScene;

    public LoginScene(Stage primaryStage){
        primary = primaryStage;
    }

    public void setUp(){

        primary.setTitle("myBalance");

        primary.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        primary.setResizable(false);    //TODO : blocco dimensione schermata, da sostituire in seguito

        HomeScene home = new HomeScene(primary);
        home.setUp();

        LoginController controller = new LoginController(primary);

        Image homeImg = new Image("File:///C://Users//darkd//OneDrive//Desktop//immagini proto//logo.png");
        ImageView view = new ImageView(homeImg);
        view.setFitHeight(200);
        view.setFitWidth(320);

        Label userNameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        TextField userNameText = new TextField();
        PasswordField passwordText = new PasswordField();

        Button regButt = new Button("Register");

        Button signInButt = new Button("Sign In");
        signInButt.setOnAction(e -> controller.checkLogin(userNameText,passwordText,home));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        GridPane.setConstraints(userNameLabel,0,0);
        GridPane.setConstraints(userNameText,1,0);
        GridPane.setConstraints(passwordLabel,0,1);
        GridPane.setConstraints(passwordText,1,1);

        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(userNameLabel,userNameText,passwordLabel,passwordText);

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(regButt,signInButt);

        VBox center = new VBox(20);
        center.getChildren().addAll(view,grid,buttons);
        center.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(center);

        lScene = new Scene(layout,800,400);

    }

    public void display(){
        primary.setScene(lScene);
        primary.show();
    }

    //METODO GESTIONE CHIUSURA
    private void closeProgram(){
        boolean result = ConfirmBox.display("Exit","Are you sure to exit?",true);
        if(result) {
            primary.close();
        }
    }

}
