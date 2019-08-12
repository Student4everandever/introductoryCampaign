package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class to resolve edit university specialties request
 */
public class EditUniversitySpecialties implements Command {

    private final static Logger logger = LogManager.getLogger(EditUniversitySpecialties.class);

    /**
     * Returns string with path to servlet for page to edit university specialties and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("id");
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));

        Optional<String[]> specialtiesToAdd = Optional.ofNullable(request.getParameterValues("add_specialty"));
        Optional<String[]> specialtiesToRemove = Optional.ofNullable(request.getParameterValues("remove_specialty"));

        if(idString == null) {
            idString = (String) request.getSession().getAttribute("universityId");
        }

        request.getSession().setAttribute("universityId", idString);

        int id = Integer.parseInt(idString);
        University university = universityService.getUniversityById(id);

        if (request.getParameter("submitted") == null || request.getParameter("submitted").equals("false")) {
            List<Specialty> universitySpecialties = specialtyService.getUniversitySpecialties(university);
            List<Specialty> nonUniversitySpecialties = specialtyService.getNonUniversitySpecialties(university);
            request.setAttribute("universitySpecialties", universitySpecialties);
            request.setAttribute("nonUniversitySpecialties", nonUniversitySpecialties);
            request.setAttribute("university", university);
            request.setAttribute("page", page);
            return "/WEB-INF/admin/edit_university_specialties.jsp";
        }

        List<Specialty> specialties = specialtyService.getAllSpecialties();
        List<Specialty> universitySpecialties = specialtyService.getUniversitySpecialties(university);

/*
        if (specialtiesToAdd.isPresent()) {
            for (Specialty specialty : specialties) {
                for (String s : specialtiesToAdd.get()) {
                    if (specialty.getTitle().equals(s) || specialty.getTitle_ukr().equals(s)) {
                        universitySpecialties.add(specialty);
                    }
                }
            }
        }
*/

        specialtiesToAdd.ifPresent(strings -> Arrays.stream(strings)
                .forEach(s -> universitySpecialties.addAll(specialties.stream()
                        .filter(specialty -> specialty.getTitle().equals(s)
                                || specialty.getTitle_ukr().equals(s))
                        .collect(Collectors.toList()))));


        specialtiesToRemove.ifPresent(strings -> Arrays.stream(strings)
                .forEach(s -> universitySpecialties
                        .removeIf(specialty -> specialty.getTitle().equals(s)
                                || specialty.getTitle_ukr().equals(s))));

        universityService.editUniversitySpecialties(university, universitySpecialties);
        List<Specialty> nonUniversitySpecialties = specialtyService.getNonUniversitySpecialties(university);

        request.setAttribute("message", WebPagesMessages.UNIVERSITY_SPECIALTIES_UPDATED);
        request.setAttribute("universitySpecialties", universitySpecialties);
        request.setAttribute("nonUniversitySpecialties", nonUniversitySpecialties);
        request.setAttribute("university", university);
        request.setAttribute("page", page);
        logger.info(String.format(LoggerMessages.ADMIN_EDIT_UNIVERSITY_SPECIALTIES, university));
        return "/WEB-INF/admin/edit_university_specialties.jsp";
    }
}

