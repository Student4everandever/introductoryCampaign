package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUniversityCommand implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUniversityCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("university_id");

        if (idString != null && UniversityService.deleteUniversity(Integer.parseInt(idString))) {
            logger.info(String.format(Messages.ADMIN_DELETE_UNIVERSITY_SUCCESS, Integer.parseInt(idString)));
            return "redirect:/campaign/admin/show_universities";
        }
        logger.error(Messages.ADMIN_DELETE_UNIVERSITY_FAIL);
        return "redirect:/campaign/exception";
    }
}