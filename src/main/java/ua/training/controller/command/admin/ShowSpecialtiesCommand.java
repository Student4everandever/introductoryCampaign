package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowSpecialtiesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        final int ROW_NUMBER = 8;

        int numberOfPages = specialtyService.getNumberOfPages(ROW_NUMBER);

        if (request.getParameter("page") == null) {
            return "/campaign/admin/show_specialties?page=1";
        }
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if (pageNumber > 0 && numberOfPages >= pageNumber) {
            List<Specialty> allSpecialtiesByPage = specialtyService.getSpecialtiesPerPage(ROW_NUMBER, pageNumber);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("allSpecialtiesByPage", allSpecialtiesByPage);
            return "/WEB-INF/admin/show_specialties.jsp";
        } else {
            return "/WEB-INF/admin/show_specialties" + (pageNumber > 0 ? numberOfPages : 1);
        }
    }
}
