package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Subject;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve adding subject request
 */
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

        if (!subjectService.validateSubjectData(name, name_ukr)) {

            request.setAttribute("error", WebPagesMessages.PROHIBITED_CHARACTERS);
            logger.warn(LoggerMessages.VALIDATION_FAIL);
            return "/WEB-INF/admin/add_subject.jsp";
        }

        subject.setName(name);
        subject.setName_ukr(name_ukr);

        if (subjectService.subjectExists(subject)) {
            request.setAttribute("error", WebPagesMessages.SUBJECT_IN_THE_SYSTEM);
            logger.warn(String.format(LoggerMessages.ADMIN_ADD_SUBJECT_ALREADY_EXIST, subject.getName(), subject.getName_ukr()));
            return "/WEB-INF/admin/add_subject.jsp";
        }

        subjectService.addSubject(subject);

        request.setAttribute("message", WebPagesMessages.SUBJECT_ADDED);
        logger.info(String.format(LoggerMessages.ADMIN_ADD_SUBJECT_SUCCESS, subject.getName(), subject.getName_ukr()));
        return "/WEB-INF/admin/add_subject.jsp";
    }
}
