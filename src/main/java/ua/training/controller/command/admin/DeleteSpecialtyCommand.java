package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve deleting specialty request
 */
public class DeleteSpecialtyCommand implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteSpecialtyCommand.class);

    /**
     * Returns string with path to servlet for page to delete specialty and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("specialty_id");

        if (idString != null && specialtyService.deleteSpecialty(Integer.parseInt(idString))) {
            logger.info(String.format(LoggerMessages.ADMIN_DELETE_SPECIALTY_SUCCESS, Integer.parseInt(idString)));
            return "redirect:/campaign/admin/show_specialties";
        }
        logger.error(LoggerMessages.ADMIN_DELETE_SPECIALTY_FAIL);
        return "redirect:/campaign/exception";
    }
}
