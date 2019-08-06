package ua.training.controller.exceptions;

public class LoginAlreadyExistException extends Exception {
    private String login;
    private String email;


    public LoginAlreadyExistException(String message, String login, String email) {
        super(message);
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }
    public String getEmail() {
        return email;
    }

}
