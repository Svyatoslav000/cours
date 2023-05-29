package com.example.app;
import javafx.scene.control.TextField;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnectoin;// Объявляем переменную для хранения экземпляра подключения
    public Connection getDbConnectoin() // Этот метод подключения к базе данных
        throws ClassNotFoundException, SQLException{// Указываем строку подключения
        String connectionString = "jdbc:mysql://"+dbHost +":" + dbPort + "/"+dbName;
        Class.forName("com.mysql.jdbc.Driver");// Загружаем драйвер
        dbConnectoin = DriverManager.getConnection(connectionString, dbUser, dbPass);// Устанавливаем соединение и возвращаем его
        return dbConnectoin;
    }
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {// Этот метод добавляет нового пользователя в базу данных
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + ","+ Const.USER_OWN+","+Const.USER_DATE+ ","+Const.USER_KOL + ")"+"VALUES(?,?,?,?)";// Задаем SQL-запрос для вставки
        PreparedStatement prSt = getDbConnectoin().prepareStatement(insert);// Подготавливаем запрос, используя соединение
        prSt.setString(1, user.getName());// Задаем параметры
        prSt.setString(2, user.getOwn());
        prSt.setString(3, user.getKol());
        prSt.setString(4, user.getDate());
        prSt.executeUpdate();// Выполняем запрос
    }
    public User getUser(TextField idconference, TextField nameField, TextField ownField, TextField kolField, TextField dateField){// Этот метод получает информацию о пользователе из базы данных
        String sql = "SELECT * FROM conference  WHERE idconference = ?";
        try (PreparedStatement statement = getDbConnectoin().prepareStatement(sql)) {
            int id = Integer.parseInt(idconference.getText());
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");// Получаем информацию о пользователе из результата запроса
                    String own = resultSet.getString("own");
                    String kol = resultSet.getString("kol");
                    String date = resultSet.getString("date");
                    // Заполняем текстовые поля полученными значениями
                    nameField.setText(name);
                    ownField.setText(own);
                    kolField.setText(kol);
                    dateField.setText(date);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void deleteUser(TextField idconference) {// Этот метод удаляет пользователя из базы данных
        String query = "DELETE FROM conference WHERE id = ?";// Задаем SQL-запрос для удаления
        try {
            PreparedStatement statement = dbConnectoin.prepareStatement(query);
            int id = Integer.parseInt(idconference.getText());
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user, TextField idconference) {// Этот метод обновляет информацию о пользователе в базе данных
        String query = "UPDATE conference SET name = ?, own = ?, kol=?, date=? WHERE id = ?";// Задаем SQL-запрос для обновления
        try {
            PreparedStatement statement = dbConnectoin.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getOwn());
            statement.setString(3, user.getKol());
            statement.setString(4, user.getDate());
            int id = Integer.parseInt(idconference.getText());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

