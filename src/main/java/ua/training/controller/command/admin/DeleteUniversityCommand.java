package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve deleting university request
 */
public class DeleteUniversityCommand implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUniversityCommand.class);

    /**
     * Returns string with path to servlet for page to delete university and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("university_id");
        String page = request.getParameter("page");

        if (idString != null && universityService.deleteUniversity(Integer.parseInt(idString))) {
            request.setAttribute("page", page);
            logger.info(String.format(LoggerMessages.ADMIN_DELETE_UNIVERSITY_SUCCESS, Integer.parseInt(idString)));
            return "/campaign/admin/show_universities";
        }
        logger.error(LoggerMessages.ADMIN_DELETE_UNIVERSITY_FAIL);
        return "redirect:/campaign/exception";
    }
}