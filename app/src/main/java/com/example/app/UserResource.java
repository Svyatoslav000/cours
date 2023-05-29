package com.example.app;

import javafx.scene.control.TextField;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/conference")
public class UserResource {

    private  DatabaseHandler dbHandler = new DatabaseHandler();// Создаем объект класса, отвечающего за обработку данных в базе данных
    // Определяем поля для хранения значений, которые передаются в метод getUserbyId
    // Поля должны быть private для корректной работы
    private TextField idconference;
    private TextField nameField;
    private TextField ownField;
    private TextField kolField;
    private TextField dateField;

    // Метод для получения пользователя по id
    @GET
    @Path("/{idconference}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserbyId(@PathParam("idconference") TextField idconference, TextField nameField, TextField ownField, TextField kolField, TextField dateField) {
        this.idconference = idconference;// Сохраняем значения параметров в полях класса
        this.nameField = nameField;
        this.ownField = ownField;
        this.kolField = kolField;
        this.dateField = dateField;
        User user = dbHandler.getUser(idconference, nameField, ownField, kolField, dateField);// Извлекаем пользователя по id с помощью метода класса DatabaseHandler
        return user;// Возвращаем пользователя в формате JSON
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)// Метод для получения списка всех пользователей
    public List<User> getAllUsers() {
        List<User> conference = new ArrayList<>();
// код для извлечения всех пользователей
        return conference;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void signUpUser(User user) {// Метод для регистрации нового пользователя
        try {
            dbHandler.signUpUser(user);// Вызываем метод класса DatabaseHandler для регистрации нового пользователя
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @DELETE
    @Path("/{TextField idconference}")    // Метод для удаления пользователя по id
    public void deleteUser(@PathParam("idconference") TextField idconference) {// Вызываем метод класса DatabaseHandler для удаления пользователя
        dbHandler.deleteUser(idconference);
    }
}
