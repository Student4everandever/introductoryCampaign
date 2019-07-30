package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.services.SpecialtyService;
import ua.training.model.services.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class EditSpecialtyCommand implements Command {

    private final static Logger logger = LogManager.getLogger(EditSpecialtyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("id");
        String title = request.getParameter("title");
        String title_ukr = request.getParameter("title_ukr");
        Optional<String[]> subjectsToAdd1 = Optional.ofNullable(request.getParameterValues("subject_2"));
        Optional<String[]> subjectsToAdd2 = Optional.ofNullable(request.getParameterValues("subject_3"));
        String message;
        String error;

        if(idString == null) {
            return "redirect:/campaign/admin/show_specialties";
        }

        int id = Integer.parseInt(idString);
        Specialty specialty = SpecialtyService.getSpecialtyById(id);

        if (title == null || title.equals("")
                || title_ukr == null || title_ukr.equals("")
                || request.getParameter("submitted") == null) {
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("specialty", specialty);
            request.setAttribute("subjects", specialtySubjects);
            return "/WEB-INF/admin/edit_specialty.jsp";
        }

        List<Subject> subjects2;
        int number = 2;
        if(subjectsToAdd1.isPresent()) {

            subjects2 = SubjectService.setSubjectsList(subjectsToAdd1.get(), number);
        } else {
            subjects2 = SubjectService.getSubjectsBySpecialtyAndNumber(specialty, number);
        }

        List<Subject> subjects3;

        if(subjectsToAdd2.isPresent()) {
            number = 3;
            subjects3 = SubjectService.setSubjectsList(subjectsToAdd2.get(), number);

        } else {
            subjects3 = SubjectService.getSubjectsBySpecialtyAndNumber(specialty, number);
        }

        specialty.setTitle(title);
        specialty.setTitle_ukr(title_ukr);

        if (!SpecialtyService.validateSpecialtyData(title, title_ukr)) {

            error = "You input prohibited character";
            request.setAttribute("error", error);
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("specialty", specialty);
            request.setAttribute("subjects", specialtySubjects);
            logger.warn(Messages.VALIDATION_FAIL);
            return "/WEB-INF/admin/edit_specialty.jsp";
        }

        if (SpecialtyService.specialtyExists(specialty)) {
            error = "The specialty already in the system";
            request.setAttribute("error", error);
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("specialty", specialty);
            request.setAttribute("subjects", specialtySubjects);
            logger.warn(String.format(Messages.ADMIN_EDIT_SPECIALTY_ALREADY_EXIST, specialty.getTitle(), specialty.getTitle_ukr()));
            return "/WEB-INF/admin/edit_specialty.jsp";
        }

        SpecialtyService.editSpecialty(specialty, subjects2, subjects3);
        message = "Specialty was successfully updated";
        List<Subject> specialtySubjects = SubjectService.getAllButFirst();
        request.setAttribute("specialty", specialty);
        request.setAttribute("subjects", specialtySubjects);
        request.setAttribute("message", message);
        logger.info(String.format(Messages.ADMIN_EDIT_SPECIALTY_SUCCESS, specialty.getTitle(), specialty.getTitle_ukr()));
        return "/WEB-INF/admin/edit_specialty.jsp";
    }
}
