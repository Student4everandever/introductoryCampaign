package ua.training.controller.filters;

import ua.training.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter to restrict access for non authorized users
 */
public class AccessFilter implements Filter {

    /**
     * Initializes filter
     * @param filterConfig parameters to setup filter
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Resolves if user can access certain pages according to user role
     * @param servletRequest request to server, contains user data
     * @param servletResponse response of server
     * @param filterChain invokes next filter in the chain
     * @throws IOException input/output exception
     * @throws ServletException general exception of a servlet
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String login = (String) (((HttpServletRequest) servletRequest).getSession().getAttribute("userLogin"));
        Role role = (Role) (((HttpServletRequest) servletRequest).getSession().getAttribute("userRole"));
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();

        if (path.contains("admin")) {
            if (login != null && role.equals(Role.ADMIN)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                throw new RuntimeException();
            }
        } else if (path.contains("applicant")) {
            if (login != null && role.equals(Role.APPLICANT)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                throw new RuntimeException();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Destroys filter
     */
    @Override
    public void destroy() {
    }
}
