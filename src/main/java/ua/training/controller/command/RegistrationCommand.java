package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private final static Logger logger = LogManager.getLogger(RegistrationCommand.class);

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
        String error;
        User user;

        if (name == null || name.equals("") || nameUkr == null || nameUkr.equals("")
                || lastName == null || lastName.equals("") || lastNameUkr == null || lastNameUkr.equals("")
                || login == null || login.equals("") || password == null || password.equals("")
                || eMail == null || eMail.equals("")) {

            return "/registration.jsp";
        }

        if(!userService.checkIfExist(login, eMail)) {

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
        } else {
            error = "Sorry, chosen login or e-mail already in the system. Choose different one";
            request.setAttribute("error", error);
            logger.error(String.format(Messages.REGISTRATION_ALREADY_EXIST, login, eMail));
            return "/registration.jsp";
        }

        System.out.println(user);
        System.out.println(userService.validateUserData(user));
        System.out.println(CommandUtility.userIsLogged(request, user.getLogin()));

        if (userService.validateUserData(user) && !(CommandUtility.userIsLogged(request, user.getLogin()))) {

            userService.createUser(user);
            CommandUtility.addUserToLoggedUsers(request, user.getLogin(), user.getRole());
            logger.info(String.format(Messages.REGISTRATION_SUCCESSFUL_REGISTRATION, user.getRole(), login));
            return "/campaign/" + user.getRole().toString().toLowerCase() + "/" + user.getRole().toString().toLowerCase() + "_base";
        } else {
            logger.error(String.format(Messages.REGISTRATION_FAIL_REGISTRATION, login));
            return "/registration.jsp";
        }
    }
}


