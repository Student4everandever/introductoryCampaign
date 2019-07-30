package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.University;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;

public class EditUniversityCommand implements Command {

    private final static Logger logger = LogManager.getLogger(EditUniversityCommand.class);

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
        logger.info(String.format(Messages.ADMIN_EDIT_UNIVERSITY_NAME, university));
        return "redirect:/campaign/admin/edit_university";
    }
}

