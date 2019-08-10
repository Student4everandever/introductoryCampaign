package ua.training.controller.command;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class to resolve user login request
 */
public class LoginCommand implements Command {
    private final static Logger logger = LogManager.getLogger(LoginCommand.class);

    private Map<String, String> pages = new HashMap<>();

    public LoginCommand() {

        pages.put("login", "/login.jsp");
        pages.put("admin", "redirect:admin/admin_base");
        pages.put("applicant", "redirect:applicant/applicant_base");
    }

    /**
     * Returns string with path to servlet for login page and stores data about user in session
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String error;

        if (login == null || login.equals("") || pass == null || pass.equals("")) {

            return "/login.jsp";
        }

        Optional<User> user = userService.findUserByLogin(login);
        String passHashed = DigestUtils.md5Hex(request.getParameter("pass"));

        if (user.isPresent() && passHashed.equals(user.get().getPassword())) {

            if (CommandUtility.userIsLogged(request, user.get().getLogin())) {
                error = "This user is already logged";
                request.setAttribute("error", error);
                logger.warn(String.format(Messages.LOGIN_LOGGED_ALREADY, user.get().getRole(), login));
                return "/login.jsp";
            }

            CommandUtility.addUserToLoggedUsers(request, user.get().getLogin(), user.get().getRole());
            logger.info(String.format(Messages.LOGIN_SUCCESSFUL_LOGIN, user.get().getRole(), login));
            return pages.getOrDefault(user.get().getRole().name().toLowerCase(), pages.get("login"));
        }
        error = "Wrong login or password";
        request.setAttribute("error", error);
        logger.error(String.format(Messages.LOGIN_FAIL_LOGIN, login));
        return pages.getOrDefault(login, pages.get("login"));
    }
}
