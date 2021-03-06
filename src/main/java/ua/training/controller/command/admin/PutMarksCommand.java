package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Subject;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to resolve put marks request
 */
public class PutMarksCommand implements Command {

    private final static Logger logger = LogManager.getLogger(PutMarksCommand.class);

    /**
     * Returns string with path to servlet for page to input users marks and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        double index1 = 0.2, index2 = 0.5, index3 = 0.2;
        String userLogin = request.getParameter("login");
        String mark1 = request.getParameter("mark1");
        String mark2 = request.getParameter("mark2");
        String mark3 = request.getParameter("mark3");
        Optional<User> user;

        if(userLogin == null) {
            userLogin = (String) request.getSession().getAttribute("user");
        }

        request.getSession().setAttribute("user", userLogin);

        if( !(user = userService.findUserByLogin(userLogin)).isPresent()) {
            request.setAttribute("error", WebPagesMessages.USER_NOT_FOUND);
            return "/WEB-INF/admin/users_with_exams.jsp";
        }

        if((mark1 == null || mark1.equals("") ||
                mark2 == null || mark2.equals("") ||
                mark3 == null || mark3.equals(""))) {
            List<Subject> userSubjects = subjectService.getUserSubjects(user.get());
            request.setAttribute("subjects", userSubjects);
            request.setAttribute("user", user.get());
            return "/WEB-INF/admin/put_marks.jsp";
        }

        List<String> userMarks = new ArrayList<>();
        userMarks.add(mark1);
        userMarks.add(mark2);
        userMarks.add(mark3);

        if(!userService.validateMarks(userMarks)) {
            List<Subject> userSubjects = subjectService.getUserSubjects(user.get());
            request.setAttribute("subjects", userSubjects);
            request.setAttribute("user", user.get());
            request.setAttribute("error", WebPagesMessages.WRONG_INPUT);
            logger.warn(LoggerMessages.VALIDATION_FAIL);
            return "/WEB-INF/admin/put_marks.jsp";
        }

        int rating = (int) (index1 * Integer.valueOf(mark1) + index2 * Integer.valueOf(mark2) + index3 * Integer.valueOf(mark3));
        userService.putUserMarks(userMarks, rating, user.get());

        request.setAttribute("message", WebPagesMessages.MARKS_ADDED);
        logger.info(String.format(LoggerMessages.ADMIN_PUT_MARKS_SUCCESS, user.get()));
        return "/campaign/admin/users_with_exams";
    }
}
