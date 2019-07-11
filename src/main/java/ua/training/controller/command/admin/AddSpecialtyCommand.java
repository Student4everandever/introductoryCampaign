package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;
import ua.training.model.services.SpecialtyService;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddSpecialtyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String university = request.getParameter("university");
        String title = request.getParameter("title");
        String title_ukr = request.getParameter("title_ukr");

        List<University> allUniversities = UniversityService.getAllUniversities();
        request.setAttribute("universities", allUniversities);



        if (title == null || title.equals("") || title_ukr == null || title_ukr.equals("")) {
            return "/WEB-INF/admin/add_specialty.jsp";
        }
        Specialty specialty = new Specialty();
        String message;
        String error;

        if (!SpecialtyService.validateSpecialtyData(title, title_ukr)) {

            error = "You input prohibited character";
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        specialty.setTitle(title);
        specialty.setTitle_ukr(title_ukr);

        if (SpecialtyService.specialtyExists(specialty)) {
            error = "The specialty already in the system";
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        SpecialtyService.addSpecialty(specialty);
        message = "Specialty was successfully added to base";

        request.setAttribute("message", message);
        return "/WEB-INF/admin/add_specialty.jsp";
    }
}
