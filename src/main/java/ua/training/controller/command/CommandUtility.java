package ua.training.controller.command;

import ua.training.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

@SuppressWarnings("unchecked")
class CommandUtility {

    /**
     * Logs users out
     * @param request HttpServletRequest with logged users
     * @param login user login to log out
     */
    static void logUserOut(HttpServletRequest request, String login) {
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute("loggedUsers");

        loggedUsers.remove(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        HttpSession session = request.getSession();

        session.setAttribute("userLogin", null);
        session.setAttribute("userRole", null);

    }

    /**
     * Checks if user is logged in
     * @param request HttpServletRequest with logged users
     * @param login user login to check
     * @return boolean value true if user logged, false - if not logged
     */
    static boolean userIsLogged(HttpServletRequest request, String login){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        return loggedUsers.stream().anyMatch(login::equals);
    }

    /**
     * Adds user to collection of logged users
     * @param request HttpServletRequest with logged users
     * @param login user login to add
     * @param role user role to add
     */
    static void addUserToLoggedUsers(HttpServletRequest request, String login, Role role) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        HttpSession session = request.getSession();

        session.setAttribute("userLogin", login);
        session.setAttribute("userRole", role);
    }
}
