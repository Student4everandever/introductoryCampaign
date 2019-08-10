package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UsersWithExamsCommand implements Command {

    /**
     * Returns string with path to servlet for showing users hwo applied for exams page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<User> usersWithExams = userService.getUsersWithExams();
        request.setAttribute("users", usersWithExams);
        return "/WEB-INF/admin/users_with_exams.jsp";
    }
}
