package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JDBCUserImpl implements UserDao {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private UserMapper userMapper = new UserMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("SQLRequests");


    @Override
    public void create(User user) throws RuntimeException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlRequest.getString("user_create"))) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getName_ukr());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getLastName_ukr());
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setString(6, user.getLogin());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getEmail());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> result = Optional.empty();


        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("user_find_by_login"))) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = Optional.of(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
