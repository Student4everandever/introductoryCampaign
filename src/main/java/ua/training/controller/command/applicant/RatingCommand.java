package ua.training.controller.command.applicant;

import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Class to resolve applicants rating request
 */
public class RatingCommand implements Command {

    /**
     * Returns string with path to servlet for rating page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        final int ROW_NUMBER = 5;
        String userLogin = (String) request.getSession().getAttribute("userLogin");
        Optional<User> user = userService.findUserByLogin(userLogin);

        if(user.isPresent() && subjectService.getUserSubjects(user.get()).size() == 0) {
            request.setAttribute("error", WebPagesMessages.NO_REQUEST);
            return "/campaign/applicant/applicant_base";
        }

        int numberOfPages = userService.getNumberOfPages(ROW_NUMBER);

        if((user.isPresent() && user.get().getRating() == 0) || numberOfPages == 0) {
            request.setAttribute("error", WebPagesMessages.RATINGS_ARE_NOT_READY);
            return "/campaign/applicant/applicant_base";
        }

        if (request.getParameter("page") == null) {
            return "/campaign/applicant/form_applicant_rating?page=1";
        }
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if (pageNumber > 0 && numberOfPages >= pageNumber) {
            List<User> allUsersWithRatingByPage = userService.getUsersPerPage(ROW_NUMBER, pageNumber);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("allUsersByPage", allUsersWithRatingByPage);
            return "/WEB-INF/applicant/form_applicants_rating.jsp";
        } else {
            return "/WEB-INF/applicant/form_applicants_rating" + (pageNumber > 0 ? numberOfPages : 1);
        }
    }
}