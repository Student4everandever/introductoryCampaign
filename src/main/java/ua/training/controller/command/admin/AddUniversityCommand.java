package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve adding university request
 */
public class AddUniversityCommand implements Command {

    private final static Logger logger = LogManager.getLogger(AddUniversityCommand.class);

    /**
     * Returns string with path to servlet for page to add university and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        int page = Integer.parseInt(request.getParameter("page"));

        System.out.println(page);

        String name = request.getParameter("name");
        String name_ukr = request.getParameter("name_ukr");

        if (name == null || name.equals("") || name_ukr == null || name_ukr.equals("")) {
            request.setAttribute("page", page);
            return "/WEB-INF/admin/add_university.jsp";
        }
        University university = new University();
        String message;
        String error;

        if (!universityService.validateUniversityData(name, name_ukr)) {

            error = "You input prohibited character";
            request.setAttribute("error", error);
            request.setAttribute("page", page);
            logger.warn(Messages.VALIDATION_FAIL);
            return "/WEB-INF/admin/add_university.jsp";
        }

        university.setName(name);
        university.setName_ukr(name_ukr);

        if (universityService.universityExists(university)) {
            error = "The university already in the system";
            request.setAttribute("error", error);
            request.setAttribute("page", page);
            logger.warn(String.format(Messages.ADMIN_ADD_UNIVERSITY_ALREADY_EXIST, university.getName(), university.getName_ukr()));
            return "/WEB-INF/admin/add_university.jsp";
        }

        universityService.addUniversity(university);
        message = "University was successfully added to base";

        request.setAttribute("message", message);
        request.setAttribute("page", page);
        logger.info(String.format(Messages.ADMIN_ADD_UNIVERSITY_SUCCESS, university.getName(), university.getName_ukr()));
        return "/WEB-INF/admin/add_university.jsp";
    }
}
