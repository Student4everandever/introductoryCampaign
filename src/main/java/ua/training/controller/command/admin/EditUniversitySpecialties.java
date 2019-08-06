package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EditUniversitySpecialties implements Command {

    private final static Logger logger = LogManager.getLogger(EditUniversitySpecialties.class);

    @Override
    public String execute(HttpServletRequest request) {

        String idString = request.getParameter("id");
        int page = Integer.parseInt(request.getParameter("page"));

        System.out.println(page);

        Optional<String[]> specialtiesToAdd = Optional.ofNullable(request.getParameterValues("add_specialty"));
        Optional<String[]> specialtiesToRemove = Optional.ofNullable(request.getParameterValues("remove_specialty"));
        String message;

        if (idString == null) {
            return "redirect:/campaign/admin/show_universities";
        }

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

        message = "University specialties were successfully updated";
        request.setAttribute("message", message);
        request.setAttribute("universitySpecialties", universitySpecialties);
        request.setAttribute("nonUniversitySpecialties", nonUniversitySpecialties);
        request.setAttribute("university", university);
        request.setAttribute("page", page);
        logger.info(String.format(Messages.ADMIN_EDIT_UNIVERSITY_SPECIALTIES, university));
        return "/WEB-INF/admin/edit_university_specialties.jsp";
    }
}

