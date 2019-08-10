package ua.training.controller.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter to set response and request to "UTF-8"
 */
public class EncodingFilter implements Filter {

    /**
     * Initializes filter
     * @param filterConfig parameters to setup filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Sets encoding of the response and request to "UTF-8"
     * @param servletRequest request to server, contains user data
     * @param servletResponse response of server
     * @param filterChain invokes next filter in the chain
     * @throws IOException input/output exception
     * @throws ServletException general exception of a servlet
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * Destroys filter
     */
    @Override
    public void destroy() {
    }
}
