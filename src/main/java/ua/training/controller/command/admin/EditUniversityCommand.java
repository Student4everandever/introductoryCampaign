package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;
import ua.training.model.services.SpecialtyService;
import ua.training.model.services.UniversityService;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EditUniversityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String name_ukr = request.getParameter("name_ukr");
        String message;

        if(idString == null) {
            return "redirect:/campaign/admin/show_universities";
        }

        int id = Integer.parseInt(idString);
        University university = UniversityService.getUniversityById(id);

        if (name == null || name.equals("")
                || name_ukr == null || name_ukr.equals("")
                || request.getParameter("submitted") == null) {
            request.setAttribute("university", university);
            return "/WEB-INF/admin/edit_university.jsp";
        }

        university.setName(name);
        university.setName_ukr(name_ukr);

        UniversityService.editUniversity(university);
        message = "University was successfully updated";
        request.setAttribute("message", message);
        return "redirect:/campaign/admin/edit_university";
    }
}

