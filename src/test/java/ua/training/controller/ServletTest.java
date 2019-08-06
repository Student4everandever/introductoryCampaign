package ua.training.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ServletTest {

    private final static String path = "login";
    private final static String page = "/login.jsp";

    @Test
    public void doGetTest() throws IOException, ServletException {

        final Servlet servlet = new Servlet();

        final ServletContext context = mock(ServletContext .class);
        final ServletConfig servletConfig = mock(ServletConfig.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(servletConfig.getServletContext()).thenReturn(context);
        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestURI()).thenReturn(path);
        when(request.getRequestDispatcher(page)).thenReturn(dispatcher);

        servlet.init(servletConfig);
        servlet.doGet(request, response);

        verify(request,times(1)).getRequestDispatcher(page);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPostTest() throws IOException, ServletException {
        final Servlet servlet = new Servlet();

        final ServletContext context = mock(ServletContext .class);
        final ServletConfig servletConfig = mock(ServletConfig.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(servletConfig.getServletContext()).thenReturn(context);
        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestURI()).thenReturn(path);
        when(request.getRequestDispatcher(page)).thenReturn(dispatcher);

        servlet.init(servletConfig);
        servlet.doPost(request, response);

        verify(request,times(1)).getRequestDispatcher(page);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }
}