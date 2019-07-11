package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.University;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        List<University> allUniversities = UniversityService.getAllUniversities();
        request.setAttribute("universities", allUniversities);
        return "/WEB-INF/admin/admin_base.jsp";
    }
}
