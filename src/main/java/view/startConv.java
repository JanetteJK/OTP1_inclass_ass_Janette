package view;
import java.io.IOException;

import controller.tempController;
import datasource.MariaDBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class startConv extends Application {
    tempController controller;

    @Override
    public void init(){
        System.out.println("INIT CALLED\n");
    }

    @Override
    public void start(Stage stage) throws IOException {
        MariaDBConnection.connect();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tempconv.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setTitle("Temperature converter");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[]args) {
        launch(args);
    }
}
