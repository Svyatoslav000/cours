package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PoiskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField p_id;

    @FXML
    private Button p_search;

    @FXML
    private TextField r_kol;

    @FXML
    private TextField r_name;

    @FXML
    private Button r_on_main;

    @FXML
    private TextField r_own;

    @FXML
    private TextField sign_date;

    @FXML
    void initialize() {
        r_on_main.setOnAction(event -> {
            r_on_main.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        p_search.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();// Создание объекта класса DatabaseHandler для работы с базой данных
            dbHandler.getUser(p_id, r_name, r_own, r_kol, sign_date);// Вызов метода getUser с параметрами для поиска информации о пользователе в базе данных
        });

    }
}
