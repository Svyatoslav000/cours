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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class signInController {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button reg_on_main;

    @FXML
    private TextField sign_date;

    @FXML
    private TextField sign_kol;

    @FXML
    private TextField sign_name;

    @FXML
    private Button sign_on_main;

    @FXML
    private TextField sign_own;

    @FXML
    void initialize() {
        sign_on_main.setOnAction(event -> {
            signUpNewUser();//вызов метода регистрации новой конференции

        });

        reg_on_main.setOnAction(event -> {
            reg_on_main.getScene().getWindow().hide();

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

   }

    private void signUpNewUser() {// Метод регистрации новой конференции
        DatabaseHandler dbHandler = new DatabaseHandler();// Создание объекта класса DatabaseHandler для работы с базой данных
        String nameText = sign_name.getText().trim();// Получение значений полей для регистрации из формы
        String ownText = sign_own.getText().trim();
        String dateText = sign_date.getText().trim();
        String kolText = sign_kol.getText().trim();
        if (!nameText.equals("") && !dateText.equals("") && !ownText.equals("") && !kolText.equals("") && isValidDate(dateText) && isNumeric(kolText) && !isNumeric(nameText) && !isNumeric(ownText)){// Проверка заполнения полей и валидности данных
            User user = new User(nameText,ownText, dateText, kolText);// Создание объекта пользователя

            sign_on_main.getScene().getWindow().hide();

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
            try {// Попытаться зарегистрировать нового пользователя в базе данных
                dbHandler.signUpUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);// Выбросить исключение, если регистрация не удалась
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);// Выбросить исключение, если класс не найден
            }
        }
        else {
            System.out.println("Вы не до конца заполнили данные");// Если поля не заполнены или данные невалидны
            sign_on_main.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("poiskResult.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    public static boolean isValidDate(String dateString) {// Метод для проверки валидности даты
            try {
                LocalDate date = LocalDate.parse(dateString);
                return !date.isBefore(LocalDate.now());
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    public static boolean isNumeric(String str) {// Метод для проверки является ли строка числом
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}


