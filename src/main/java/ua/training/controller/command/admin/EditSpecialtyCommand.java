package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Class to resolve edit specialty request
 */
public class EditSpecialtyCommand implements Command {

    private final static Logger logger = LogManager.getLogger(EditSpecialtyCommand.class);

    /**
     * Returns string with path to servlet for page to edit specialty and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("id");
        String title = request.getParameter("title");
        String title_ukr = request.getParameter("title_ukr");
        Optional<String[]> subjectsToAdd1 = Optional.ofNullable(request.getParameterValues("subject_2"));
        Optional<String[]> subjectsToAdd2 = Optional.ofNullable(request.getParameterValues("subject_3"));
        String message;
        String error = "";

        if(idString == null) {
            return "redirect:/campaign/admin/show_specialties";
        }

        if(title.equals("") && title_ukr.equals("")
                && !subjectsToAdd1.isPresent() && !subjectsToAdd2.isPresent()
                && request.getParameter("submitted") != null) {
            error = WebPagesMessages.CHOOSE_NEW_DATA;
        }
        int id = Integer.parseInt(idString);
        Specialty specialty = specialtyService.getSpecialtyById(id);

        List<Subject> exam2 = subjectService.getSubjectsBySpecialtyAndNumber(specialty, 2);
        List<Subject> exam3 = subjectService.getSubjectsBySpecialtyAndNumber(specialty, 3);

        if(title.equals("")) {
            title = specialty.getTitle();
        }

        if(title_ukr.equals("")) {
            title_ukr = specialty.getTitle_ukr();
        }


        if ((title == null || title_ukr == null)
                || request.getParameter("submitted") == null) {
            setPage(request, specialty, exam2, exam3);
            return "/WEB-INF/admin/edit_specialty.jsp";
        }

        specialty.setTitle(title);
        specialty.setTitle_ukr(title_ukr);

        if (!specialtyService.validateSpecialtyData(title, title_ukr)) {

            request.setAttribute("error", WebPagesMessages.PROHIBITED_CHARACTERS);
            setPage(request, specialty, exam2, exam3);
            logger.warn(LoggerMessages.VALIDATION_FAIL);
            return "/WEB-INF/admin/edit_specialty.jsp";
        }

        List<Subject> subjects2;
        int number = 2;
        if(subjectsToAdd1.isPresent()) {

            subjects2 = subjectService.setSubjectsList(subjectsToAdd1.get());
        } else {
            subjects2 = subjectService.getSubjectsBySpecialtyAndNumber(specialty, number);
        }

        List<Subject> subjects3;
        number = 3;
        if(subjectsToAdd2.isPresent()) {
            subjects3 = subjectService.setSubjectsList(subjectsToAdd2.get());

        } else {
            subjects3 = subjectService.getSubjectsBySpecialtyAndNumber(specialty, number);
        }

        specialtyService.editSpecialty(specialty, subjects2, subjects3);

        if(error.equals("")){
            message = WebPagesMessages.SPECIALTY_UPDATED;
        } else {
            message = "";
        }

        List<Subject> specialtySubjects = subjectService.getAllButFirst();
        request.setAttribute("specialty", specialty);
        request.setAttribute("subjects", specialtySubjects);
        request.setAttribute("message", message);
        request.setAttribute("error", error);
        request.setAttribute("exam2", subjects2);
        request.setAttribute("exam3", subjects3);
        logger.info(String.format(LoggerMessages.ADMIN_EDIT_SPECIALTY_SUCCESS, specialty.getTitle(), specialty.getTitle_ukr()));
        return "/WEB-INF/admin/edit_specialty.jsp";
    }

    private void setPage(HttpServletRequest request, Specialty specialty, List<Subject> exam2, List<Subject> exam3) {
        List<Subject> specialtySubjects = subjectService.getAllButFirst();
        request.setAttribute("specialty", specialty);
        request.setAttribute("subjects", specialtySubjects);
        request.setAttribute("exam2", exam2);
        request.setAttribute("exam3", exam3);
    }
}
