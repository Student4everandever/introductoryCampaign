package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class to resolve show specialties request
 */
public class ShowSpecialtiesCommand implements Command {

    /**
     * Returns string with path to servlet for specialties page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        final int ROW_NUMBER = 6;

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
