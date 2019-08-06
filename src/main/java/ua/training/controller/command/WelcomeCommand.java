package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;

import javax.servlet.http.HttpServletRequest;


public class WelcomeCommand implements Command {
    private final static Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String userLogin = (String) request.getSession().getAttribute("userLogin");
        CommandUtility.logUserOut(request, userLogin);
        logger.info(String.format(Messages.LOG_LOGOUT, userLogin));
        return "/index.jsp";
    }
}
