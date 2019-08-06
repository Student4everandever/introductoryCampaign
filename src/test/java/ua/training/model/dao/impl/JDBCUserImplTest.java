package ua.training.model.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.controller.exceptions.LoginAlreadyExistException;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class JDBCUserImplTest {

    @Mock
    private static DataSource dataSource;
    @Mock
    UserMapper userMapper;
    @InjectMocks
    private static JDBCUserImpl jdbcUser = new JDBCUserImpl();
    @Mock
    private static Connection connection;
    @Mock
    private static PreparedStatement preparedStatement;
    @Mock
    private static ResultSet resultSet;

    private User user, user2, user3;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        assertNotNull(dataSource);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(dataSource.getConnection()).thenReturn(connection);


        user = new User(1, Role.APPLICANT, "Alex", "Алекс", "Kidov", "Кiдов", "kidovalex@gmail.com", "AlexK", "234", 126, 0);
        user2 = new User(2, Role.APPLICANT, "Alex", "Алекс", "Kidov", "Кiдов", "alexkidov@ukr.net", "Alex", "234", 126, 0);
        user3 = new User(3, Role.APPLICANT, "Mark", "Марк", "Sober", "Собер", "ukrposhta@bigmir.net", "MarkS", "234", 117, 1);

        when(userMapper.extractFromResultSet(resultSet)).thenReturn(user);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("APPLICANT"); //user.getRole().name());
        when(resultSet.getString(3)).thenReturn(user.getName());
        when(resultSet.getString(4)).thenReturn(user.getName_ukr());
        when(resultSet.getString(5)).thenReturn(user.getLastName());
        when(resultSet.getString(6)).thenReturn(user.getLastName_ukr());
        when(resultSet.getString(7)).thenReturn(user.getEmail());
        when(resultSet.getString(8)).thenReturn(user.getLogin());
        when(resultSet.getString(9)).thenReturn(user.getPassword());
        when(resultSet.getInt(10)).thenReturn(user.getRating());
        when(resultSet.getInt(11)).thenReturn(user.getUniversityId());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

    }
    @Test(expected=RuntimeException.class)
    public void createWrongArgument() throws SQLException, LoginAlreadyExistException {
        jdbcUser.createWithLoginEmailExistenceCheck(null);

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(8)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).execute();
    }

    @Test
    public void createNew() throws SQLException, LoginAlreadyExistException {
        jdbcUser.createWithLoginEmailExistenceCheck(user);

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(8)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).execute();
        verify(connection, times(0)).commit();
        verify(resultSet, times(0)).next();
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByLogin() {
    }

    @Test
    public void findUsersWithExams() {
    }

    @Test
    public void putMarks() {
    }

    @Test
    public void findUserMarks() {
    }

    @Test
    public void findUsersWithRating() {
    }

    @Test
    public void getUsersPerPage() {
    }

    @Test
    public void findUserByLoginOrEmail() {
    }

    @Test
    public void findUsersWithRequiredRating() {
    }
}