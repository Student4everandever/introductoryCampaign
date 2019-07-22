package ua.training.model.dao;

import ua.training.model.entity.Subject;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByLogin(String login);

    Map<Integer, User> findUsersWithExams();

    void putMarks(List<String> userMarks, int rating, User user);

    Optional<List<Integer>> findUserMarks(User user);
}
