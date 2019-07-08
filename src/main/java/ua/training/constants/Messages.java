package ua.training.constants;

public interface Messages {
    String LOGIN_FAIL = "login_fail";
    String REGISTRATION_FAIL = "registration_fail";

    String LOGIN_MISMATCH = "invalid_login_input_message";
    String PASSWORD_MISMATCH = "invalid_password_input_message";

    String REQUEST_ALREADY_SENT = "info_request_sent";
    String NO_FREE_PLACES = "info_no_places";
    String SUCCESSFUL_REQUEST = "info_successful_request";
    String SUCCESSFUL_REGISTRATION = "info_successful_registration";

    String LOG_SUCCESSFUL_LOGIN = "%s %s logged successfully.";
    String LOG_SUCCESSFUL_REGISTRATION = "%s %s registered successfully.";
    String LOG_FAIL_LOGIN = "Sign in fail with login: %s.";
    String LOG_LOGGED_ALREADY = "Logged user %s %s tried to sign up.";
    String LOG_FAIL_REGISTRATION = "Registration fail. Login %s already exists or data invalid.";
    String LOG_LOGOUT = "%s logged out.";
    String LOG_SENT_REQUEST = "%s %s sent request %s to %s";
    String LOG_APPROVED_REQUEST = "%s %s approved request %s from %s %s";
    String LOG_ADD_REPORT = "%s %s added report %s to conference %s.";
    String LOG_REMOVE_REPORT = "%s %s removed report %s from conference %s.";
    String LOG_SUCCESSFUL_SIGN_UP = "%s %s signed up to %s conference.";
    String LOG_NO_FREE_PLACES = "%s %s tried to sign up  to %s but there were no free places.";
    String LOG_ALREADY_SIGNED_UP = "%s %s tried to sign up to %s he(she) already signed to.";
}
