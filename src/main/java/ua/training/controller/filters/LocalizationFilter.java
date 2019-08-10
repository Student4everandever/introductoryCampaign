package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {

    /**
     * Initializes filter
     * @param filterConfig parameters to setup filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        Locale myLocale = new Locale("uk");
        Locale.setDefault(myLocale);
    }

    /**
     * Sets language of the pages content according to a session parameter
     * @param servletRequest request to server, contains user data
     * @param servletResponse response of server
     * @param filterChain invokes next filter in the chain
     * @throws IOException input/output exception
     * @throws ServletException general exception of a servlet
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String lang = servletRequest.getParameter("lang");

        if (lang != null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            request.getSession().setAttribute("lang", lang);
            if (lang.equals("uk")) {
                request.getSession().setAttribute("lang", "uk");
            }
            if (lang.equals("en")) {
                request.getSession().setAttribute("lang", "en");
            }
            return;
        }
        servletRequest.getServletContext().setAttribute("lang", "en");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Destroys filter
     */
    @Override
    public void destroy() {
    }
}
