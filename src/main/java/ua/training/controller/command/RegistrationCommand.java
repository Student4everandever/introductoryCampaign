package ua.training.controller.command;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.exceptions.LoginAlreadyExistException;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve user registration request
 */
public class RegistrationCommand implements Command {

    private final static Logger logger = LogManager.getLogger(RegistrationCommand.class);

    /**
     * Returns string with path to servlet for registration page and stores data about user in session
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter("name");
        String nameUkr = request.getParameter("name_ukr");
        String lastName = request.getParameter("last_name");
        String lastNameUkr = request.getParameter("last_name_ukr");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String eMail = request.getParameter("email");
        String role = Role.APPLICANT.toString();
        User user;

        if (name == null || name.equals("") || nameUkr == null || nameUkr.equals("")
                || lastName == null || lastName.equals("") || lastNameUkr == null || lastNameUkr.equals("")
                || login == null || login.equals("") || password == null || password.equals("")
                || eMail == null || eMail.equals("")) {

            return "/registration.jsp";
        }

        user = new User.UserBuilder()
                .setRole(Role.valueOf(role))
                .setName(name)
                .setName_ukr(nameUkr)
                .setLastName(lastName)
                .setLastName_ukr(lastNameUkr)
                .setLogin(login)
                .setPassword(password)
                .setEmail(eMail)
                .build();

        if (!userService.validateUserData(user)) {
            request.setAttribute("error", WebPagesMessages.PROHIBITED_CHARACTERS);
            logger.error(String.format(LoggerMessages.REGISTRATION_FAIL_REGISTRATION, login));
            return "/registration.jsp";
        }

        user.setPassword(DigestUtils.md5Hex(password));

        try {
            userService.createUser(user);
            CommandUtility.addUserToLoggedUsers(request, user.getLogin(), user.getRole());
        } catch (LoginAlreadyExistException e) {
            request.setAttribute("error", WebPagesMessages.USER_ALREADY_EXISTS);
            logger.error(String.format(LoggerMessages.REGISTRATION_ALREADY_EXIST, e.getLogin(), e.getEmail()));
            return "/registration.jsp";
    }
        logger.info(String.format(LoggerMessages.REGISTRATION_SUCCESSFUL_REGISTRATION, user.getRole(), login));
            return "/campaign/" + user.getRole().toString().toLowerCase() + "/" + user.getRole().toString().toLowerCase() + "_base";
    }
}