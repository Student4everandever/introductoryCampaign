package ua.training.controller.command;

import ua.training.model.services.SpecialtyService;
import ua.training.model.services.SubjectService;
import ua.training.model.services.UniversityService;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface to resolve requests
 */
public interface Command {
    UniversityService universityService = new UniversityService();
    SpecialtyService specialtyService = new SpecialtyService();
    SubjectService subjectService = new SubjectService();
    UserService userService = new UserService();

    /**
     * Returns string with path to servlet depending on request's data
     * @param request HttpServletRequest with data from jsp
     * @return String with page name to go to
     */
    String execute(HttpServletRequest request);
}
