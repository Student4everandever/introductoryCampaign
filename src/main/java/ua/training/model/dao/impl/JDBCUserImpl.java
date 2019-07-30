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
import java.util.*;

public class JDBCUserImpl implements UserDao {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private UserMapper userMapper = new UserMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("sqlRequests");


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

    @Override
    public Map<Integer, User> findUsersWithExams() {

        Map<Integer, User> uniqueUsers = new HashMap<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "user_find_with_exams"))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                userMapper.makeUnique(uniqueUsers, user);
            }
            return uniqueUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putMarks(List<String> userMarks, int rating, User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement marksQuery = connection.prepareStatement(sqlRequest.getString(
                     "user_put_marks"));
             PreparedStatement ratingQuery = connection.prepareStatement(sqlRequest.getString(
                     "user_update_put_marks"))
        ) {
            connection.setAutoCommit(false);

            for (int i = 0; i < userMarks.size(); i++) {
                marksQuery.setInt(1, Integer.parseInt(userMarks.get(i)));
                marksQuery.setInt(2, i + 1);
                marksQuery.setInt(3, user.getId());
                marksQuery.executeUpdate();
            }
            ratingQuery.setInt(1, rating);
            ratingQuery.setInt(2, user.getId());
            ratingQuery.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findUserMarks(User user) {
        List<Integer> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement marksQuery = connection.prepareStatement(sqlRequest.getString(
                     "user_get_marks"))) {
            connection.setAutoCommit(false);

            for (int i = 0; i < 3; i++) {
                marksQuery.setInt(1, user.getId());
                marksQuery.setInt(2, i + 1);
                ResultSet rs = marksQuery.executeQuery();
                if (rs.next()) {
                    result.add(rs.getInt("mark"));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<User> findUsersWithRating() {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest.getString(
                     "user_find_with_rating"))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUsersPerPage(int rows, int pageNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest.getString(
                     "user_find_per_page"))) {

            ps.setInt(1, rows);
            ps.setInt(2, pageNumber * rows - rows);
            ResultSet rs = ps.executeQuery();
            List<User> result = new ArrayList<>();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findUsersWithRequiredRating(int rating) {

        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest.getString(
                     "user_find_with_required_rating"))) {
            ps.setInt(1, rating);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
