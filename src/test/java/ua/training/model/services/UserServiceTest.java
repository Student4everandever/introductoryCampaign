package ua.training.model.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private static UserDao userDao;
    @InjectMocks
    private static UserService userService = new UserService();

    private final User user1 = new User(1, Role.APPLICANT, "Alex", "Алекс", "Kidov", "Кідов", "kidovalex@gmail.com", "AlexK", "234", 126, 0);
    private final User user2 = new User(3, Role.APPLICANT, "Mark", "Марк", "Sober", "Собер", "ukrposhta@bigmir.net", "MarkS", "234", 117, 1);
    private String [] userMailsWithRatingAbove100;
    private final Map<Integer, User> usersWithExamsMap = new HashMap<>();
    private final List<User> usersWithExamsList = new ArrayList<>();
    private final List<User> usersWithRating = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userMailsWithRatingAbove100 = new String[]{user1.getEmail(), user2.getEmail()};
        usersWithExamsMap.put(1, user1);
        usersWithExamsMap.put(2, user2);
        usersWithExamsList.add(user1);
        usersWithExamsList.add(user2);
        usersWithRating.add(user1);
        usersWithRating.add(user2);

    }

    @Test
    public void appliedAlreadyTest() {

        assertTrue(userService.appliedAlready(user1));
    }

    @Test
    public void checkIfExistTest() {
        assertTrue(userService.checkIfExist(user1.getLogin(), user1.getEmail()));
    }


    @Test
    public void findUserByLogin() {
        when(userDao.findByLogin("AlexK")).thenReturn(Optional.of(user1));

        assertEquals(user1, userService.findUserByLogin("AlexK").get());
    }

    @Test
    @Ignore
    public void getUsersWithExams() {
        when(userDao.findUsersWithExams()).thenReturn(usersWithExamsMap);

        assertEquals(usersWithExamsList, userService.getUsersWithExams());
    }

    @Test
    public void validateMarks() {
        String good = "200";
        String good1 = "1";
        String good2 = "150";
        String bad = "201";
        String bad1 = "0";
        String bad2 = "-1";

        List<String> goodList = new ArrayList<>();
        goodList.add(good);
        goodList.add(good1);
        goodList.add(good2);

        List<String> badList = new ArrayList<>();
        badList.add(bad);
        badList.add(bad1);
        badList.add(bad2);

        assertTrue(userService.validateMarks(goodList));
        assertFalse(userService.validateMarks(badList));
    }

    @Test
    public void getUserMarks() {
        List<Integer> user1Marks = new ArrayList<>();
        user1Marks.add(130);
        user1Marks.add(120);
        user1Marks.add(200);

        when(userDao.findUserMarks(user1)).thenReturn(user1Marks);

        assertEquals(user1Marks, userService.getUserMarks(user1));
    }

    @Test
    public void getStudentsMails() {

        when(userDao.findUsersWithRequiredRating(100)).thenReturn(usersWithRating);

        assertArrayEquals(userMailsWithRatingAbove100, userService.getStudentsMails(100));
    }

    @Test
    public void getUsersWithRating() {
        when(userDao.findUsersWithRating()).thenReturn(usersWithRating);

        assertEquals(usersWithRating, userService.getUsersWithRating());
    }
}