package ua.training.model.dao;

import ua.training.controller.exceptions.LoginAlreadyExistException;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {

    void createWithLoginEmailExistenceCheck(User user) throws LoginAlreadyExistException;

    Optional<User> findByLogin(String login);

    Map<Integer, User> findUsersWithExams();

    void putMarks(List<String> userMarks, int rating, User user);

    List<Integer> findUserMarks(User user);

    List<User> findUsersWithRequiredRating(int rating);

    List<User> findUsersWithRating();

    List<User> getUsersPerPage(int rows, int pageNumber);

    boolean findUserByLoginOrEmail(String login, String eMail);

    List<User> findAllApplicants();
}
