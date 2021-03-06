package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.controller.exceptions.LoginAlreadyExistException;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Class that accesses the user table of DB
 */
public class JDBCUserImpl implements UserDao {

    private final static Logger logger = LogManager.getLogger(JDBCUserImpl.class);

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private UserMapper userMapper = new UserMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("sqlRequests");

    @Override
    public void createWithLoginEmailExistenceCheck(User user) throws LoginAlreadyExistException {
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
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new LoginAlreadyExistException(e.getMessage(), user.getLogin(), user.getEmail());
        } catch (SQLException e) {
            logger.error(String.format(LoggerMessages.JDBC_USER_CREATION_FAIL, user.getEmail()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllApplicants() {
        Map<Integer, User> uniqueUsers = new HashMap<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest.getString(
                     "user_find_all_applicants"))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                userMapper.makeUnique(uniqueUsers, user);
            }
            return new ArrayList<>(uniqueUsers.values());
        } catch (SQLException e) {
            logger.error(LoggerMessages.JDBC_USER_FIND_ALL_APPLICANTS_FAIL);
            throw new RuntimeException(e);
        }
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
            logger.error(String.format(LoggerMessages.JDBC_USER_FINDING_FAIL, login));
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
            logger.error(LoggerMessages.JDBC_USER_FINDING_WITH_EXAMS_FAIL);
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
            logger.error(String.format(LoggerMessages.JDBC_USER_PUTTING_MARKS_FAIL, user));
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
            logger.error(String.format(LoggerMessages.JDBC_USER_FINDING_USER_MARKS_FAIL, user));
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
            return getUsers(result, ps);
        } catch (SQLException e) {
            logger.error(LoggerMessages.JDBC_USER_FINDING_WITH_RATING_FAIL);
            throw new RuntimeException(e);
        }
    }

    private List<User> getUsers(List<User> result, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = userMapper.extractFromResultSet(rs);
            result.add(user);
        }
        return result;
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
            logger.error(String.format(LoggerMessages.JDBC_USER_GET_PER_PAGE, pageNumber, rows));
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findUserByLoginOrEmail(String login, String eMail) {
        Optional<User> result = Optional.empty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("user_find_by_login_or_email"))) {
            ps.setString(1, login);
            ps.setString(2, eMail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = Optional.of(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(String.format(LoggerMessages.JDBC_USER_FINDING_BY_LOGIN_OR_EMAIL_FAIL, login, eMail));
            throw new RuntimeException(e);
        }
        return result.isPresent();
    }

    @Override
    public List<User> findUsersWithRequiredRating(int rating) {

        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest.getString(
                     "user_find_with_required_rating"))) {
            ps.setInt(1, rating);
            return getUsers(result, ps);
        } catch (SQLException e) {
            logger.error(LoggerMessages.JDBC_USER_FIND_WITH_REQUIRED_RATING_FAIL);
            throw new RuntimeException(e);
        }
    }
}
