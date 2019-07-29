package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class RatingCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String userLogin = (String) request.getSession().getAttribute("userLogin");
        String error;
        Optional<User> user;

        if(userLogin == null || !(user = UserService.findUserByLogin(userLogin)).isPresent()) {
            return "/login.jsp";
        }

        List<Integer> marksAndRating = UserService.getUserMarks(user.get());

        if(marksAndRating.size() == 0 || user.get().getRating() == 0) {

            error = "The ratings are not ready yet";
            request.setAttribute("error", error);
            return "/campaign/applicant/applicant_base";
        }

        marksAndRating.add(user.get().getRating());
        request.setAttribute("marks", marksAndRating);
        return "/WEB-INF/applicant/form_applicants_rating.jsp";
    }
}
