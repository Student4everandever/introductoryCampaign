package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;
import ua.training.model.entity.User;
import ua.training.model.services.SpecialtyService;
import ua.training.model.services.UniversityService;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChooseSpecialtyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");
        String error;

        if(universityString == null) {
            List<University> allUniversities = UniversityService.getAllUniversities();
            request.setAttribute("universities", allUniversities);
            return "/WEB-INF/applicant/choose_specialty.jsp";
        }

        String userLogin = (String) request.getSession().getAttribute("userLogin");
        Optional<User> user = UserService.findUserByLogin(userLogin);

        if (user.isPresent() && UserService.appliedAlready(user.get())) {

            error = "You already applied";
            request.setAttribute("error", error);
            return "/campaign/applicant/applicant_base";
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
