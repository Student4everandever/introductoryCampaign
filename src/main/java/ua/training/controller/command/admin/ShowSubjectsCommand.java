package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSubjectsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        request.setAttribute("subjects", allSubjects);
        return "/WEB-INF/admin/show_subjects.jsp";
    }
}
