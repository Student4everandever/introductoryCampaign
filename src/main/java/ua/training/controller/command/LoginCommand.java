package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.model.entity.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {
    private final static Logger logger = LogManager.getLogger(LoginCommand.class);

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

        Optional<User> user = UserService.findUserByLogin(login);

        if (user.isPresent() && pass.equals(user.get().getPassword())) {

            if (CommandUtility.checkUserIsLogged(request, user.get().getLogin(), user.get().getRole())) {
                logger.warn(String.format(Messages.LOGIN_LOGGED_ALREADY, user.get().getRole(), login));
                return "/WEB-INF/error.jsp";
            }

            CommandUtility.addUserToLoggedUsers(request, user.get().getLogin(), user.get().getRole());
            logger.info(String.format(Messages.LOGIN_SUCCESSFUL_LOGIN, user.get().getRole(), login));
            return pages.getOrDefault(user.get().getRole().name().toLowerCase(), pages.get("login"));
        }
        logger.error(String.format(Messages.LOGIN_FAIL_LOGIN, login));
        return pages.getOrDefault(login, pages.get("login"));
    }
}
