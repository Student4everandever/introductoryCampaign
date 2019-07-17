package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.services.SpecialtyService;

import javax.servlet.http.HttpServletRequest;

public class DeleteSpecialtyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String idString = request.getParameter("specialty_id");

        if (idString != null && SpecialtyService.deleteSpecialty(Integer.parseInt(idString))) {
            return "redirect:/campaign/admin/show_specialties";
        }

        return "redirect:/campaign/exception";
    }
}
