package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Filter to manage displaying language
 */
public class LocalizationFilter implements Filter {

    /**
     * Initializes filter
     *
     * @param filterConfig parameters to setup filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        Locale myLocale = new Locale("en");
        Locale.setDefault(myLocale);
    }

    /**
     * Sets language of the pages content according to a session parameter
     *
     * @param servletRequest  request to server, contains user data
     * @param servletResponse response of server
     * @param filterChain     invokes next filter in the chain
     * @throws IOException      input/output exception
     * @throws ServletException general exception of a servlet
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getParameter("locale") != null) {
            req.getSession().setAttribute("language", req.getParameter("locale"));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Destroys filter
     */
    @Override
    public void destroy() {
    }
}
