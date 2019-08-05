package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class DeleteUniversityCommand implements Command {

    private final static Logger logger = LogManager.getLogger(DeleteUniversityCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("university_id");
        String page = request.getParameter("page");


        if (idString != null && universityService.deleteUniversity(Integer.parseInt(idString))) {
            request.setAttribute("page", page);
            logger.info(String.format(Messages.ADMIN_DELETE_UNIVERSITY_SUCCESS, Integer.parseInt(idString)));
            return "/campaign/admin/show_universities";
        }
        logger.error(Messages.ADMIN_DELETE_UNIVERSITY_FAIL);
        return "redirect:/campaign/exception";
    }
}