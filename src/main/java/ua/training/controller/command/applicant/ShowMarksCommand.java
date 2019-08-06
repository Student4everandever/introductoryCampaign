package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ShowMarksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String userLoginString = request.getParameter("login");
        String error;

        System.out.println(userLoginString);

        if(userLoginString == null) {
            return "/campaign/applicant/form_applicant_rating";
        }

        Optional<User> user = userService.findUserByLogin(userLoginString);
        if(!user.isPresent()) {
            error = "Can not find user";
            request.setAttribute("error", error);
            return "/campaign/applicant/form_applicants_rating";
        }

        List<Integer> marksAndRating = userService.getUserMarks(user.get());

        marksAndRating.add(user.get().getRating());
        request.setAttribute("marks", marksAndRating);
        return "/WEB-INF/applicant/show_marks.jsp";
    }
}
