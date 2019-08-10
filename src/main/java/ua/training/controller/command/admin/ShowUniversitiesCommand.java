package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUniversitiesCommand implements Command {

    /**
     * Returns string with path to servlet for universities page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        final int ROW_NUMBER = 6;

        int numberOfPages = universityService.getNumberOfPages(ROW_NUMBER);

        if (request.getParameter("page") == null) {
            request.setAttribute("page", 1);
            return "/campaign/admin/show_universities?page=1";
        }
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if (pageNumber > 0 && numberOfPages >= pageNumber) {
            List<University> allUniversitiesByPage = universityService.getUniversitiesPerPage(ROW_NUMBER, pageNumber);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("allUniversitiesByPage", allUniversitiesByPage);
            request.setAttribute("page", pageNumber);
            return "/WEB-INF/admin/show_universities.jsp";
        } else {
            request.setAttribute("page", pageNumber);
            return "/WEB-INF/admin/show_universities" + (pageNumber > 0 ? numberOfPages : 1);
        }
    }
}
