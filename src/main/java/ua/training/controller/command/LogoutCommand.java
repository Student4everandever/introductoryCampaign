package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve user logout request
 */
public class LogoutCommand implements Command {

    private final static Logger logger = LogManager.getLogger(LogoutCommand.class);

    /**
     * Returns string with path to servlet for main page and logs user out
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String userLogin = (String) request.getSession().getAttribute("userLogin");
        CommandUtility.logUserOut(request, userLogin);
        logger.info(String.format(LoggerMessages.LOG_LOGOUT, userLogin));
        return "/index.jsp";
    }
}
