package ua.training.controller.command.applicant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChooseSubjectsCommand implements Command {

    private final static Logger logger = LogManager.getLogger(ChooseSubjectsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");
        University university = universityService.getUniversityByName(universityString);
        String specialtyString = request.getParameter("specialty");
        Specialty specialty = specialtyService.getSpecialtyByNameString(specialtyString);

        int number = 2;

        String subject1String = request.getParameter("subject_1");
        String subject2String = request.getParameter("subject_2");
        String subject3String = request.getParameter("subject_3");

        if(subject2String == null || subject3String == null) {
            List<Subject> specialtySubjects2 = subjectService.getSubjectsBySpecialtyAndNumber(specialty, number);
            number = 3;
            List<Subject> specialtySubjects3 = subjectService.getSubjectsBySpecialtyAndNumber(specialty, number);
            request.setAttribute("university", university);
            request.setAttribute("specialty", specialty);
            request.setAttribute("subjects2", specialtySubjects2);
            request.setAttribute("subjects3", specialtySubjects3);

            return "/WEB-INF/applicant/choose_subjects.jsp";
        }

        List<Subject> subjectsToAdd = new ArrayList<>();
        subjectsToAdd.add(subjectService.findSubjectByString(subject1String));
        subjectsToAdd.add(subjectService.findSubjectByString(subject2String));
        subjectsToAdd.add(subjectService.findSubjectByString(subject3String));

        String userLogin = String.valueOf(request.getSession().getAttribute("userLogin"));
        Optional<User> user = userService.findUserByLogin(userLogin);

        if(user.isPresent()) {

            subjectService.addSubjectsToUser(user.get(), university, specialty, subjectsToAdd);
            String message = "Thank you for choosing our service. You are successfully registered for exams";
            request.setAttribute("message", message);
            request.setAttribute("applied", message);
            logger.info(String.format(Messages.APPLICANT_CHOOSE_SUBJECTS_REGISTERED, user.get().getLogin()));
            return "/WEB-INF/applicant/applicant_base.jsp";
        }
        logger.error(Messages.APPLICANT_CHOOSE_SUBJECTS_FAIL);
        return "/login.jsp";
    }
}
