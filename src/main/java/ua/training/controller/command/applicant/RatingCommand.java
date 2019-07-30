package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RatingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        final int ROW_NUMBER = 5;
        String error;

        int numberOfPages = UserService.getNumberOfPages(ROW_NUMBER);

        if (numberOfPages == 0) {
            error = "The ratings are not ready yet";
            request.setAttribute("error", error);
            return "/campaign/applicant/applicant_base";
        }

        if (request.getParameter("page") == null) {
            return "/campaign/applicant/form_applicant_rating?page=1";
        }
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if (pageNumber > 0 && numberOfPages >= pageNumber) {
            List<User> allUsersWithRatingByPage = UserService.getUsersPerPage(ROW_NUMBER, pageNumber);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("allUsersByPage", allUsersWithRatingByPage);
            return "/WEB-INF/applicant/form_applicants_rating.jsp";
        } else {
            return "/WEB-INF/applicant/form_applicants_rating" + (pageNumber > 0 ? numberOfPages : 1);
        }
    }
}