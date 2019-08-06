package ua.training.controller.filters;

import ua.training.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String login = (String) (((HttpServletRequest) servletRequest).getSession().getAttribute("userLogin"));
        Role role = (Role)(((HttpServletRequest) servletRequest).getSession().getAttribute("userRole"));
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        if(path.contains("admin")) {
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

    @Override
    public void destroy() {
    }
}
