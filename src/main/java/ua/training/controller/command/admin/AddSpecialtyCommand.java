package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Class to resolve adding specialty request
 */
public class AddSpecialtyCommand implements Command {

    private final static Logger logger = LogManager.getLogger(AddSpecialtyCommand.class);

    /**
     * Returns string with path to servlet for page to add specialty and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");
        String title = request.getParameter("title");
        String title_ukr = request.getParameter("title_ukr");
        Optional<String[]> subjectsToAdd1 = Optional.ofNullable(request.getParameterValues("subject_2"));
        Optional<String[]> subjectsToAdd2 = Optional.ofNullable(request.getParameterValues("subject_3"));

        List<University> allUniversities = universityService.getAllUniversities();
        request.setAttribute("universities", allUniversities);

        if (title == null || title.equals("") || title_ukr == null || title_ukr.equals("") || universityString == null) {
            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            return "/WEB-INF/admin/add_specialty.jsp";
        }
        Specialty specialty = new Specialty();
        University university = universityService.getUniversityByName(universityString);

        List<Subject> subjects2;
        List<Subject> subjects3;
        if(!subjectsToAdd1.isPresent() || !subjectsToAdd2.isPresent()) {

            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", WebPagesMessages.CHOOSE_SUBJECTS_TO_PUT);
            return "/WEB-INF/admin/add_specialty.jsp";
        } else {
            subjects2 = subjectService.setSubjectsList(subjectsToAdd1.get());
            subjects3 = subjectService.setSubjectsList(subjectsToAdd2.get());
        }

        if (!specialtyService.validateSpecialtyData(title, title_ukr)) {

            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", WebPagesMessages.PROHIBITED_CHARACTERS);
            logger.warn(LoggerMessages.VALIDATION_FAIL);
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        specialty.setTitle(title);
        specialty.setTitle_ukr(title_ukr);

        if (specialtyService.specialtyExists(specialty)) {
            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", WebPagesMessages.SPECIALTY_IN_THE_SYSTEM);
            logger.warn(String.format(LoggerMessages.ADMIN_ADD_SPECIALTY_ALREADY_EXIST, specialty.getTitle(), specialty.getTitle_ukr()));
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        specialtyService.addSpecialty(specialty, university, subjects2, subjects3);
        List<Subject> specialtySubjects = subjectService.getAllButFirst();
        request.setAttribute("subjects", specialtySubjects);
        request.setAttribute("message", WebPagesMessages.SPECIALTY_ADDED);
        logger.info(String.format(LoggerMessages.ADMIN_ADD_SPECIALTY_SUCCESS, specialty.getTitle(), specialty.getTitle_ukr()));
        return "/WEB-INF/admin/add_specialty.jsp";
    }
}
