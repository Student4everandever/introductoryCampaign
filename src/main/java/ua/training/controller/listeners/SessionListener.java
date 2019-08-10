package ua.training.controller.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class SessionListener implements HttpSessionListener {

    /**
     * Receives notification that a session has been created
     * @param httpSessionEvent Containing the session
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * Receives notification that a session is about to be invalidated
     * @param httpSessionEvent Containing the session
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext().getAttribute("loggedUsers");
        String login = (String) httpSessionEvent.getSession().getAttribute("userLogin");
        loggedUsers.remove(login);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
