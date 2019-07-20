package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Subject;
import ua.training.model.entity.User;
import ua.training.model.services.SubjectService;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PutMarksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String userLogin = request.getParameter("login");
        String mark1 = request.getParameter("mark1");
        String mark2 = request.getParameter("mark2");
        String mark3 = request.getParameter("mark3");
        String error;
        String message;
        Optional<User> user;

        if(userLogin == null || !(user = UserService.findUserByLogin(userLogin)).isPresent()) {
            return "/login.jsp";
        }

        System.out.println(user.get());
        System.out.println(mark1);
        System.out.println(mark2);
        System.out.println(mark3);

        if((mark1 == null || mark1.equals("") ||
                mark2 == null || mark2.equals("") ||
                mark3 == null || mark3.equals(""))) {
            List<Subject> userSubjects = SubjectService.getUserSubjects(user.get());
            request.setAttribute("subjects", userSubjects);
            request.setAttribute("user", user.get());
            return "/WEB-INF/admin/put_marks.jsp";
        }

        List<String> userMarks = new ArrayList<>();
        userMarks.add(mark1);
        userMarks.add(mark2);
        userMarks.add(mark3);

        if(!UserService.validateMarks(userMarks)) {
            error = "You can put only numbers from 1 up to 200";
            List<Subject> userSubjects = SubjectService.getUserSubjects(user.get());
            request.setAttribute("subjects", userSubjects);
            request.setAttribute("user", user.get());
            request.setAttribute("error", error);
            return "/WEB-INF/admin/put_marks.jsp";
        }

        UserService.putUserMarks(userMarks, user.get());
        message = "Marks were successfully added";

        request.setAttribute("message", message);
        return "/campaign/admin/users_with_exams";
    }
}
