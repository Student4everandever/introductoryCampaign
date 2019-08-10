package ua.training.model.entity;

//import ua.training.model.entity.enums.Role;

import ua.training.model.entity.enums.Role;

import java.util.Objects;

public class User {
    private int id;
    private Role role;
    private String name;
    private String name_ukr;
    private String lastName;
    private String lastName_ukr;
    private String email;
    private String login;
    private String password;
    private int rating;
    private int universityId;

    public User() {
    }

    public User(int id, Role role, String name, String name_ukr, String lastName, String lastName_ukr, String email, String login, String password, int rating, int universityId) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.name_ukr = name_ukr;
        this.lastName = lastName;
        this.lastName_ukr = lastName_ukr;
        this.email = email;
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.universityId = universityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ukr() {
        return name_ukr;
    }

    public void setName_ukr(String name_ukr) {
        this.name_ukr = name_ukr;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName_ukr() {
        return lastName_ukr;
    }

    void setLastName_ukr(String lastName_ukr) {
        this.lastName_ukr = lastName_ukr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUniversityId() {
        return universityId;
    }

    void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", name_ukr='" + name_ukr + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastName_ukr='" + lastName_ukr + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                ", universityId=" + universityId +
                '}';
    }

    public static final class UserBuilder {
        private int id;
        private Role role;
        private String name;
        private String name_ukr;
        private String lastName;
        private String lastName_ukr;
        private String email;
        private String login;
        private String password;
        private int rating;
        private int universityId;

        public UserBuilder() {
        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setName_ukr(String name_ukr) {
            this.name_ukr = name_ukr;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setLastName_ukr(String lastName_ukr) {
            this.lastName_ukr = lastName_ukr;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public UserBuilder setUniversityId(int universityId) {
            this.universityId = universityId;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setRole(role);
            user.setName(name);
            user.setName_ukr(name_ukr);
            user.setLastName(lastName);
            user.setLastName_ukr(lastName_ukr);
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(password);
            user.setRating(rating);
            user.setUniversityId(universityId);
            return user;
        }
    }
}
