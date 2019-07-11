package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.University;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUniversityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("university_id");

        if (idString != null && UniversityService.deleteUniversity(Integer.parseInt(idString))) {
            return "redirect:/campaign/admin/admin_base";
        }

        return "redirect:/campaign/exception";
    }
}