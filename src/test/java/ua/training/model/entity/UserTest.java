package ua.training.model.entity;

import org.junit.Before;
import org.junit.Test;
import ua.training.model.entity.enums.Role;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setup(){
        user = new User(1, Role.APPLICANT, "Alex", "Алекс", "Kidov", "Кидов", "alexkidov@ukr.net", "AlexK", "234", 127, 1);
    }

    @Test
    public void getId() {

        assertEquals(1, user.getId());
    }

    @Test
    public void setId() {
        int idValue = 4;

        user.setId(idValue);
        assertEquals(idValue, user.getId());
    }

    @Test
    public void getName() {
        assertEquals("Alex", user.getName());
    }

    @Test
    public void setName() {
        String nameValue = "Mark";

        user.setName(nameValue);
        assertEquals(nameValue, user.getName());
    }

    @Test
    public void getName_ukr() {
        assertEquals("Алекс", user.getName_ukr());
    }

    @Test
    public void setName_ukr() {
        String name_ukrValue = "Марк";

        user.setName_ukr(name_ukrValue);
        assertEquals(name_ukrValue, user.getName_ukr());
    }

    @Test
    public void getLastName() {
        assertEquals("Kidov", user.getLastName());
    }

    @Test
    public void setLastName() {
        String lastNameValue = "Sober";

        user.setLastName(lastNameValue);
        assertEquals(lastNameValue, user.getLastName());
    }

    @Test
    public void getLastName_ukr() {
        assertEquals("Кидов", user.getLastName_ukr());
    }

    @Test
    public void setLastName_ukr() {
        String lastName_ukrValue = "Собер";

        user.setLastName_ukr(lastName_ukrValue);
        assertEquals(lastName_ukrValue, user.getLastName_ukr());
    }

    @Test
    public void getEmail() {
        assertEquals("alexkidov@ukr.net", user.getEmail());
    }

    @Test
    public void setEmail() {
        String emailValue = "alexkidov@gmail.com";

        user.setEmail(emailValue);
        assertEquals(emailValue, user.getEmail());
    }

    @Test
    public void getLogin() {
        assertEquals("AlexK", user.getLogin());
    }

    @Test
    public void setLogin() {
        String loginValue = "KidovA";

        user.setLogin(loginValue);
        assertEquals(loginValue, user.getLogin());
    }

    @Test
    public void getPassword() {
        assertEquals("234", user.getPassword());
    }

    @Test
    public void setPassword() {
        String passwordValue = "345";

        user.setPassword(passwordValue);
        assertEquals(passwordValue, user.getPassword());
    }

    @Test
    public void getRating() {
        assertEquals(127, user.getRating());
    }

    @Test
    public void setRating() {
        int ratingValue = 150;

        user.setRating(ratingValue);
        assertEquals(ratingValue, user.getRating());
    }

    @Test
    public void getUniversityId() {
        assertEquals(1, user.getUniversityId());
    }

    @Test
    public void setUniversityId() {
        int universityValue = 2;

        user.setUniversityId(universityValue);
        assertEquals(universityValue, user.getUniversityId());
    }
}