package ua.training.controller.command.applicant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChooseSpecialtyCommand implements Command {

    private final static Logger logger = LogManager.getLogger(ChooseSpecialtyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String universityString = request.getParameter("university");
        String error;

        if(universityString == null) {
            List<University> allUniversities = universityService.getAllUniversities();
            request.setAttribute("universities", allUniversities);
            return "/WEB-INF/applicant/choose_specialty.jsp";
        }

        String userLogin = (String) request.getSession().getAttribute("userLogin");
        Optional<User> user = userService.findUserByLogin(userLogin);

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
