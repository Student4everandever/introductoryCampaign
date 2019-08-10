package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve edit university name request
 */
public class EditUniversityCommand implements Command {

    private final static Logger logger = LogManager.getLogger(EditUniversityCommand.class);

    /**
     * Returns string with path to servlet for page to edit university name and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("id");
        int page = Integer.parseInt(request.getParameter("page"));
        String name = request.getParameter("name");
        String name_ukr = request.getParameter("name_ukr");
        String message;
        String error;

        if(idString == null) {
            return "redirect:/campaign/admin/show_universities";
        }

        int id = Integer.parseInt(idString);
        University university = universityService.getUniversityById(id);

        if (name == null || name.equals("")
                || name_ukr == null || name_ukr.equals("")
                || request.getParameter("submitted") == null) {
            request.setAttribute("university", university);
            request.setAttribute("page", page);
            return "/WEB-INF/admin/edit_university.jsp";
        }

        if (!universityService.validateUniversityData(name, name_ukr)) {

            error = "You input prohibited character";
            request.setAttribute("error", error);
            request.setAttribute("page", page);
            request.setAttribute("university", university);
            logger.warn(Messages.VALIDATION_FAIL);
            return "/WEB-INF/admin/edit_university.jsp";
        }

        university.setName(name);
        university.setName_ukr(name_ukr);

        universityService.editUniversity(university);
        message = "University was successfully updated";
        request.setAttribute("message", message);
        request.setAttribute("university", university);
        request.setAttribute("page", page);
        logger.info(String.format(Messages.ADMIN_EDIT_UNIVERSITY_NAME, university));
        return "/WEB-INF/admin/edit_university.jsp";
    }
}

