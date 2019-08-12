package ua.training.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to prevent browsers cashing pages,
 * so users can not go back to account with browser's back button
 */
public class NoBrowserCashFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", -1);
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
    }
}
