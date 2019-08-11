package ua.training.controller.exceptions;

/**
 * Exception class for checking if user already logged
 */
public class LoginAlreadyExistException extends Exception {

    /**
     * User login & email
     */
    private String login;
    private String email;

    /**
     * Constructs a new exception with detail message, user login and email.
     * @param message message to display
     * @param login login of the user who triggered exception
     * @param email email of the user who triggered exception
     */
    public LoginAlreadyExistException(String message, String login, String email) {
        super(message);
        this.login = login;
        this.email = email;
    }

    /**
     * Returns login of the user who triggered exception
     * @return login of the user who triggered exception
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns email of the user who triggered exception
     * @return email of the user who triggered exception
     */
    public String getEmail() {
        return email;
    }
}
