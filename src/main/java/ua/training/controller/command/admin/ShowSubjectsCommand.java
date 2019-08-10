package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSubjectsCommand implements Command {

    /**
     * Returns string with path to servlet for subjects page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        request.setAttribute("subjects", allSubjects);
        return "/WEB-INF/admin/show_subjects.jsp";
    }
}
