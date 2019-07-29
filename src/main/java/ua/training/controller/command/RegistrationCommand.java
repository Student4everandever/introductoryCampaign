package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
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

        if (name == null || name.equals("") || nameUkr == null || nameUkr.equals("")
                || lastName == null || lastName.equals("") || lastNameUkr == null || lastNameUkr.equals("")
                || login == null || login.equals("") || password == null || password.equals("")
                || eMail == null || eMail.equals("")) {

            return "/registration.jsp";
        }


/*
        System.out.println(name + " " + lastName + " "
                         + nameUkr + " " + lastNameUkr + " "
                         + login + " " + password + " "
                         + eMail + " " + role);
*/

        User user = new User.UserBuilder()
                .setRole(Role.valueOf(role))
                .setName(name)
                .setName_ukr(nameUkr)
                .setLastName(lastName)
                .setLastName_ukr(lastNameUkr)
                .setLogin(login)
                .setPassword(password)
                .setEmail(eMail)
                .build();

           if (UserService.validateUserData(user) && !(CommandUtility.checkUserIsLogged(request, user.getLogin(), user.getRole()))) {

        UserService.createUser(user);
        CommandUtility.addUserToLoggedUsers(request, user.getLogin(), user.getRole());
                return "/campaign/" + user.getRole().toString().toLowerCase() + "/" + user.getRole().toString().toLowerCase() + "_base";
            } else {
                return "/registration.jsp";
            }
    }
}


