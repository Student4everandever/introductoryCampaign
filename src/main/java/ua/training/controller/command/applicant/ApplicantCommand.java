package ua.training.controller.command.applicant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Class to resolve applicant base page request
 */
public class ApplicantCommand implements Command {
    private final static Logger logger = LogManager.getLogger(ChooseSpecialtyCommand.class);

    /**
     * Returns string with path to servlet for applicant base page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String userLogin = (String) request.getSession().getAttribute("userLogin");
        Optional<User> user = userService.findUserByLogin(userLogin);
        String applied;

        if (user.isPresent() && userService.appliedAlready(user.get())) {

            applied = "True";
            request.setAttribute("applied", applied);
            logger.error(String.format(LoggerMessages.APPLICANT_CHOOSE_SPECIALTY_SECOND_REGISTRATION_ATTEMPT, user.get().getLogin()));
        }
        return "/WEB-INF/applicant/applicant_base.jsp";
    }
}
