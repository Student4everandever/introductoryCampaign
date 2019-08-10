package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ChooseSpecialtyCommand implements Command {

    /**
     * Returns string with path to servlet for choosing specialty page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");

        if(universityString == null) {
            List<University> allUniversities = universityService.getAllUniversities();
            request.setAttribute("universities", allUniversities);
            return "/WEB-INF/applicant/choose_specialty.jsp";
        }

        List<University> chosenUniversity = new ArrayList<>();
        University university = universityService.getUniversityByName(universityString);
        chosenUniversity.add(university);
        List<Specialty> universitySpecialties = specialtyService.getUniversitySpecialties(university);
        request.setAttribute("specialties", universitySpecialties);
        request.setAttribute("universities", chosenUniversity);
        String specialtyString = request.getParameter("specialty");
        if (specialtyString == null) {

            request.setAttribute("universities", chosenUniversity);
            request.setAttribute("specialties", universitySpecialties);
            return "/WEB-INF/applicant/choose_specialty.jsp";
        }
        Specialty specialty = specialtyService.getSpecialtyByNameString(specialtyString);
        request.setAttribute("university", university);
        request.setAttribute("specialty", specialty);
        return "/campaign/applicant/choose_subjects";
    }
}
