package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    //private static String MESSAGE_BUNDLE_NAME = "lang";



    @Override
    public void init(FilterConfig filterConfig) {
        Locale myLocale = new Locale("uk");
        Locale.setDefault(myLocale);
    }

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

    @Override
    public void destroy() {
    }
}
