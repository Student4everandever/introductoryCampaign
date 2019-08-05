package ua.training.controller.command;

import ua.training.model.services.SpecialtyService;
import ua.training.model.services.SubjectService;
import ua.training.model.services.UniversityService;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    UniversityService universityService = new UniversityService();
    SpecialtyService specialtyService = new SpecialtyService();
    SubjectService subjectService = new SubjectService();
    UserService userService = new UserService();


    String execute(HttpServletRequest request);
}
