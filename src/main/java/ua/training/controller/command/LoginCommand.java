package ua.training.controller.command;

import ua.training.model.entity.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {

    private Map<String, String> pages = new HashMap<>();

    public LoginCommand() {

        pages.put("login", "/login.jsp");
        pages.put("admin", "redirect:admin/admin_base");
        pages.put("applicant", "redirect:applicant/applicant_base");
    }

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || login.equals("") || pass == null || pass.equals("")) {

            return "/login.jsp";
        }
        System.out.println(login + " " + pass);

        Optional<User> user = UserService.login(login);

        if (user.isPresent() && pass.equals(user.get().getPassword())) {

            if (CommandUtility.checkUserIsLogged(request, user.get().getLogin(), user.get().getRole())) {
                return "/WEB-INF/error.jsp";
            }

            CommandUtility.addUserToLoggedUsers(request, user.get().getLogin(), user.get().getRole());
            request.getSession().setAttribute("userLogin", login);
            request.getSession().setAttribute("userRole", user.get().getRole());

            return pages.getOrDefault(user.get().getRole().name().toLowerCase(), pages.get("login"));
        }
        return pages.getOrDefault(login, pages.get("login"));
    }
}
