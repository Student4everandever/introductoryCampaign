package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;
import ua.training.model.services.SpecialtyService;
import ua.training.model.services.SubjectService;
import ua.training.model.services.UniversityService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ChooseSpecialtyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");
        List<University> allUniversities = UniversityService.getAllUniversities();
        request.setAttribute("universities", allUniversities);


        if (universityString == null) {

            return "/WEB-INF/applicant/choose_specialty.jsp";
        }
        List<University> chosenUniversity = new ArrayList<>();
        University university = UniversityService.getUniversityByName(universityString);
        chosenUniversity.add(university);
        List<Specialty> universitySpecialties = SpecialtyService.getUniversitySpecialties(university);
        request.setAttribute("specialties", universitySpecialties);
        request.setAttribute("universities", chosenUniversity);
        String specialtyString = request.getParameter("specialty");
        if (specialtyString == null) {

            request.setAttribute("universities", chosenUniversity);
            request.setAttribute("specialties", universitySpecialties);
            return "/WEB-INF/applicant/choose_specialty.jsp";
        }
        Specialty specialty = SpecialtyService.getSpecialtyByNameString(specialtyString);
        request.setAttribute("university", university);
        request.setAttribute("specialty", specialty);
        return "/campaign/applicant/choose_subjects";
    }
}