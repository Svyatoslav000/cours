package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button author;

    @FXML
    private ImageView img;

    @FXML
    private Button search;

    @FXML
    private Button signUP;

    @FXML
    void initialize() {

        author.setOnAction(event -> {// Обработчик события на кнопке "автор"
            author.getScene().getWindow().hide();// Скрыть текущее окно

            FXMLLoader loader = new FXMLLoader();// Создать загрузчик FXML
            loader.setLocation(getClass().getResource("app.fxml"));// Задать путь к FXML файлу приложения
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();// Получить корневой элемент FXML файла
            Stage stage = new Stage();// Создать новое окно и задать сцену с корневым элементом
            stage.setScene(new Scene(root));
            stage.showAndWait();// Показать окно и ждать, пока пользователь закроет его
        });

        search.setOnAction(event -> {
            search.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("poisk.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        signUP.setOnAction(event -> {
            signUP.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signIn.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
