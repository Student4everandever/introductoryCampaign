package ua.training.controller;

import ua.training.controller.command.*;
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

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("logout", new LogoutCommand());
        commands.put("welcome", new WelcomeCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("admin/admin_base", new AdminCommand());
        commands.put("admin/add_university", new AddUniversityCommand());
        commands.put("admin/edit_university", new EditUniversityCommand());
        commands.put("admin/edit_university_specialties", new EditUniversitySpecialties());
        commands.put("admin/delete_university", new DeleteUniversityCommand());
        commands.put("admin/add_specialty", new AddSpecialtyCommand());
        commands.put("admin/edit_specialty", new EditSpecialtyCommand());
        commands.put("admin/delete_specialty", new DeleteSpecialtyCommand());
        commands.put("admin/add_subject", new AddSubjectCommand());
        commands.put("admin/put_marks", new PutMarksCommand());
        commands.put("admin/form_applicants_rating", new RatingCommand());
        commands.put("admin/send_mails", new MailsCommand());
        commands.put("applicant/applicant_base", new ApplicantCommand());
        commands.put("applicant/choose_specialty", new ChooseSpecialtyCommand());
        commands.put("exception", new ExceptionCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(".*/campaign/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path, (req) -> "/welcome.jsp");
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}