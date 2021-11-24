package com.mycompany.myBalance;

import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    Stage primary;
    String username = "steco2000";
    String password = "password1234";

    public LoginController(Stage primaryStage){
        primary = primaryStage;
    }

    public void checkLogin(TextField usernameText, PasswordField passwordText, HomeScene home){
        String insertedUN = usernameText.getText();
        String insertedPassw = passwordText.getText();
        if(insertedUN.equals(username) && insertedPassw.equals(password)){
            home.display();
        }else{
            AlertBox.display("Error","Incorrect username or password");
        }
    }

}
