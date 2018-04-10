package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Optional;

public class UsersDaoJdbcImpl implements UsersDao {

    //language=SQL
    private final String SQL_INSERT_INTO =
            "INSERT INTO fix_user(login,password,first_name,last_name,mobile,email) VALUES (?, ?, ?, ?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_BY_LOGIN =
            "SELECT * FROM fix_user WHERE login = ?";

    //language=SQL
    private final String SQL_SELECT_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM fix_user WHERE login = ? AND password = ?";

    private Connection connection;

    public UsersDaoJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void add(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO);
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            statement.setString(3, model.getFirstName());
            statement.setString(4, model.getLastName());
            statement.setString(5, model.getMobile());
            statement.setString(6, model.getEmail());
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public User find(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_LOGIN);
            statement.setString(1, model.getLogin());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Integer id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String mobile = resultSet.getString("mobile");
                String email = resultSet.getString("email");
                return new User(id, password,model.getLogin(),firstName,lastName,mobile,email);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }


    @Override
    public List<User> findAll() {
        return null;
    }
}
