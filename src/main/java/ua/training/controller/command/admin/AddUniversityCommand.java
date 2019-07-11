package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.University;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;

public class AddUniversityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter("name");
        String name_ukr = request.getParameter("name_ukr");

        if (name == null || name.equals("") || name_ukr == null || name_ukr.equals("")) {
            return "/WEB-INF/admin/add_university.jsp";
        }
        University university = new University();
        String message;
        String error;

        if (!UniversityService.validateUniversityData(name, name_ukr)) {

            error = "You input prohibited character";
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_university.jsp";
        }

        university.setName(name);
        university.setName_ukr(name_ukr);

        if (UniversityService.universityExists(university)) {
            error = "The university already in the system";
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_university.jsp";
        }

        UniversityService.addUniversity(university);
        message = "University was successfully added to base";

        request.setAttribute("message", message);
        return "/WEB-INF/admin/add_university.jsp";
    }
}
