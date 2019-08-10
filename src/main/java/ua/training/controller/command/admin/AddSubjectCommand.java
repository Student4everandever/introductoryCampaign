package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Subject;

import javax.servlet.http.HttpServletRequest;

public class AddSubjectCommand implements Command {

    private final static Logger logger = LogManager.getLogger(AddSubjectCommand.class);

    /**
     * Returns string with path to servlet for page to add subject and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter("name");
        String name_ukr = request.getParameter("name_ukr");

        if (name == null || name.equals("") || name_ukr == null || name_ukr.equals("")) {
            return "/WEB-INF/admin/add_subject.jsp";
        }
        Subject subject = new Subject();
        String message;
        String error;

        if (!subjectService.validateSubjectData(name, name_ukr)) {

            error = "You input prohibited character";
            request.setAttribute("error", error);
            logger.warn(Messages.VALIDATION_FAIL);
            return "/WEB-INF/admin/add_subject.jsp";
        }

        subject.setName(name);
        subject.setName_ukr(name_ukr);

        if (subjectService.subjectExists(subject)) {
            error = "The subject already in the system";
            request.setAttribute("error", error);
            logger.warn(String.format(Messages.ADMIN_ADD_SUBJECT_ALREADY_EXIST, subject.getName(), subject.getName_ukr()));
            return "/WEB-INF/admin/add_subject.jsp";
        }

        subjectService.addSubject(subject);
        message = "Subject was successfully added to base";

        request.setAttribute("message", message);
        logger.info(String.format(Messages.ADMIN_ADD_SUBJECT_SUCCESS, subject.getName(), subject.getName_ukr()));
        return "/WEB-INF/admin/add_subject.jsp";
    }
}
