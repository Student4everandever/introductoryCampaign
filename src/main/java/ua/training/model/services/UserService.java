package ua.training.model.services;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entity.Subject;
import ua.training.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ua.training.constants.Regex.*;

public class UserService {

    private static UserDao userDao = DaoFactory.getInstance().createUserDao();

    public static boolean validateUserData(User user) {
        return user.getName().matches(REGEX_NAME_LAT) &&
                user.getName_ukr().matches(REGEX_NAME_UKR) &&
                user.getLastName().matches(REGEX_NAME_LAT) &&
                user.getLastName_ukr().matches(REGEX_NAME_UKR) &&
                user.getEmail().matches(REGEX_E_MAIL) &&
                user.getLogin().matches(REGEX_LOGIN) &&
                user.getPassword().matches(REGEX_PASSWORD);
    }


    /*
        public static List<User> getAllSpeakers() {
            return userDao.findAllSpeakers();
        }

        public static boolean signUpIsPossible(String login, int conferenceId) {
            int userId;
            Optional<User> user = userDao.findByLogin(login);
            if (user.isPresent()) {
                userId = user.get().getId();
                return !userDao.getVisitorsConferences(userId, conferenceId);
            }
            return true;
        }


        public static User getSpeakerById(int id) {
            return userDao.findById(id);
        }

        public static Optional<User> login(String login) {
            return userDao.findByLogin(login);
        }
    */
    public static void createUser(User user) {
        userDao.create(user);
    }

    public static Optional<User> findUserByLogin(String userLogin) {
        return userDao.findByLogin(userLogin);
    }

    public static List<User> getUsersWithExams() {
        Map<Integer, User> usersWithExams = userDao.findUsersWithExams();
        return new ArrayList<>(usersWithExams.values());
    }

    public static boolean validateMarks(List<String> userMarks) {
        for (String mark : userMarks) {
            if (!mark.matches(REGEX_USER_MARKS)) {
                return false;
            }
        }
        return true;
    }

    public static void putUserMarks(List<String> userMarks, User user) {
        userDao.putMarks(userMarks, user);
    }
}

