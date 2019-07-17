package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;
import ua.training.model.services.SpecialtyService;
import ua.training.model.services.SubjectService;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AddSpecialtyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");
        String title = request.getParameter("title");
        String title_ukr = request.getParameter("title_ukr");
        Optional<String[]> subjectsToAdd1 = Optional.ofNullable(request.getParameterValues("subject_2"));
        Optional<String[]> subjectsToAdd2 = Optional.ofNullable(request.getParameterValues("subject_3"));

        List<University> allUniversities = UniversityService.getAllUniversities();
        request.setAttribute("universities", allUniversities);



        if (title == null || title.equals("") || title_ukr == null || title_ukr.equals("") || universityString == null) {
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            return "/WEB-INF/admin/add_specialty.jsp";
        }
        Specialty specialty = new Specialty();
        University university = UniversityService.getUniversityByName(universityString);
        String message;
        String error;

        List<Subject> subjects2;
        List<Subject> subjects3;
        int number = 2;
        if(!subjectsToAdd1.isPresent() || !subjectsToAdd2.isPresent()) {

            error = "Please choose subjects to put";
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_specialty.jsp";
        } else {
            subjects2 = SubjectService.setSubjectsList(subjectsToAdd1.get(), number);
            number = 3;
            subjects3 = SubjectService.setSubjectsList(subjectsToAdd2.get(), number);
        }

        if (!SpecialtyService.validateSpecialtyData(title, title_ukr)) {

            error = "You input prohibited character";
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        specialty.setTitle(title);
        specialty.setTitle_ukr(title_ukr);

        if (SpecialtyService.specialtyExists(specialty)) {
            error = "The specialty already in the system";
            List<Subject> specialtySubjects = SubjectService.getAllButFirst();
            request.setAttribute("subjects", specialtySubjects);
            request.setAttribute("error", error);
            return "/WEB-INF/admin/add_specialty.jsp";
        }

        SpecialtyService.addSpecialty(specialty, university, subjects2, subjects3);
        message = "Specialty was successfully added to base";
        List<Subject> specialtySubjects = SubjectService.getAllButFirst();
        request.setAttribute("subjects", specialtySubjects);
        request.setAttribute("message", message);
        return "/WEB-INF/admin/add_specialty.jsp";
    }
}
