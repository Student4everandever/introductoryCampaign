package ua.training.controller.command.applicant;

import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Class to resolve applicant show marks request
 */
public class ShowMarksCommand implements Command {

    /**
     * Returns string with path to servlet for showing marks page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        String userLoginString = request.getParameter("login");

        if(userLoginString == null) {
            userLoginString = (String) request.getSession().getAttribute("user");
        }

        request.getSession().setAttribute("user", userLoginString);

        Optional<User> user = userService.findUserByLogin(userLoginString);
        if(!user.isPresent()) {
            request.setAttribute("error", WebPagesMessages.USER_NOT_FOUND);
            return "/campaign/applicant/form_applicants_rating";
        }

        List<Integer> marksAndRating = userService.getUserMarks(user.get());

        marksAndRating.add(user.get().getRating());
        request.setAttribute("marks", marksAndRating);
        return "/WEB-INF/applicant/show_marks.jsp";
    }
}
