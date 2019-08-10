package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.LoginCommand;
import ua.training.controller.command.LogoutCommand;
import ua.training.controller.command.RegistrationCommand;
import ua.training.controller.command.admin.*;
import ua.training.controller.command.applicant.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class that manages get and post requests and sends responses
 */
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Servlet initialization, creates collection with commands and it's paths (triggers)
     * @param servletConfig configuration object
     */
    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogoutCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("admin/admin_base", new AdminCommand());
        commands.put("admin/show_universities", new ShowUniversitiesCommand());
        commands.put("admin/show_specialties", new ShowSpecialtiesCommand());
        commands.put("admin/show_subjects", new ShowSubjectsCommand());
        commands.put("admin/add_university", new AddUniversityCommand());
        commands.put("admin/edit_university", new EditUniversityCommand());
        commands.put("admin/edit_university_specialties", new EditUniversitySpecialties());
        commands.put("admin/delete_university", new DeleteUniversityCommand());
        commands.put("admin/add_specialty", new AddSpecialtyCommand());
        commands.put("admin/edit_specialty", new EditSpecialtyCommand());
        commands.put("admin/delete_specialty", new DeleteSpecialtyCommand());
        commands.put("admin/add_subject", new AddSubjectCommand());
        commands.put("admin/users_with_exams", new UsersWithExamsCommand());
        commands.put("admin/put_marks", new PutMarksCommand());
        commands.put("admin/send_mails", new MailsCommand());
        commands.put("applicant/applicant_base", new ApplicantCommand());
        commands.put("applicant/choose_specialty", new ChooseSpecialtyCommand());
        commands.put("applicant/choose_subjects", new ChooseSubjectsCommand());
        commands.put("applicant/form_applicant_rating", new RatingCommand());
        commands.put("applicant/show_marks", new ShowMarksCommand());
    }

    /**
     * Handles a GET request
     * @param request request information for HTTP servlet
     * @param response response of the servlet to user
     * @throws IOException input/output exception
     * @throws ServletException general exception of a servlet
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Handles a POST request
     * @param request request information for HTTP servlet
     * @param response response of the servlet to user
     * @throws IOException input/output exception
     * @throws ServletException general exception of a servlet
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Resolves method to choose according to request URI
     * @param request request information for HTTP servlet
     * @param response response of the servlet to user
     * @throws IOException input/output exception
     * @throws ServletException general exception of a servlet
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/campaign/", "");
        Command command = commands.getOrDefault(path, (req) -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}