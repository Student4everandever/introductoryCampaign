package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
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
        String message;
        String error;

        List<Subject> subjects2;
        List<Subject> subjects3;
        if(!subjectsToAdd1.isPresent() || !subjectsToAdd2.isPresent()) {

            error = "Please choose subjects to put";
            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_specialty.jsp";
        } else {
            subjects2 = subjectService.setSubjectsList(subjectsToAdd1.get());
            subjects3 = subjectService.setSubjectsList(subjectsToAdd2.get());
        }

        if (!specialtyService.validateSpecialtyData(title, title_ukr)) {

            error = "You input prohibited character";
            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", error);
            logger.warn(Messages.VALIDATION_FAIL);
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        specialty.setTitle(title);
        specialty.setTitle_ukr(title_ukr);

        if (specialtyService.specialtyExists(specialty)) {
            error = "The specialty already in the system";
            List<Subject> specialtySubjects = subjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", error);
            logger.warn(String.format(Messages.ADMIN_ADD_SPECIALTY_ALREADY_EXIST, specialty.getTitle(), specialty.getTitle_ukr()));
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        specialtyService.addSpecialty(specialty, university, subjects2, subjects3);
        message = "Specialty was successfully added to base";
        List<Subject> specialtySubjects = subjectService.getAllButFirst();
        request.setAttribute("subjects", specialtySubjects);
        request.setAttribute("message", message);
        logger.info(String.format(Messages.ADMIN_ADD_SPECIALTY_SUCCESS, specialty.getTitle(), specialty.getTitle_ukr()));
        return "/WEB-INF/admin/add_specialty.jsp";
    }
}
