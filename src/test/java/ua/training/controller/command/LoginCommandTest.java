package ua.training.controller.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandUtility.class)
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*"})
public class LoginCommandTest {

    @Test
    public void executeNoLoginNoPassword() {
        final LoginCommand command = new LoginCommand();

        final String pageLogin = "/login.jsp";
        final String login = "AlexK";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        PowerMockito.mockStatic(CommandUtility.class);

        when(CommandUtility.userIsLogged(request, login)).thenReturn(false);

        assertEquals(pageLogin, command.execute(request));
    }

    @Test
    public void executeWrongLoginOrPassword() {
        final LoginCommand command = new LoginCommand();

        final String pageLogin = "/login.jsp";
        final String login = "Wrong";
        final String pass = "987";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        PowerMockito.mockStatic(CommandUtility.class);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("pass")).thenReturn(pass);
        when(CommandUtility.userIsLogged(request, login)).thenReturn(false);

        assertEquals(pageLogin, command.execute(request));
    }

    @Test
    public void executeLoggedUser() {
        final LoginCommand command = new LoginCommand();

        final String pageError = "/WEB-INF/404.jsp";
        final String login = "AlexK";
        final String pass = "234";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        PowerMockito.mockStatic(CommandUtility.class);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("pass")).thenReturn(pass);
        when(CommandUtility.userIsLogged(request, login)).thenReturn(true);

        assertEquals(pageError, command.execute(request));
    }

    @Test
    public void executeApplicantLogin() {
        final LoginCommand command = new LoginCommand();

        final String pageApplicant = "redirect:applicant/applicant_base";
        final String login = "AlexK";
        final String pass = "234";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        PowerMockito.mockStatic(CommandUtility.class);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("pass")).thenReturn(pass);
        when(CommandUtility.userIsLogged(request, login)).thenReturn(false);

        assertEquals(pageApplicant, command.execute(request));
    }


    @Test
    public void executeAdminLogin() {
        final LoginCommand command = new LoginCommand();

        final String pageAdmin = "redirect:admin/admin_base";
        final String login = "Admin";
        final String pass = "123";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        PowerMockito.mockStatic(CommandUtility.class);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("pass")).thenReturn(pass);
        when(CommandUtility.userIsLogged(request, login)).thenReturn(false);

        assertEquals(pageAdmin, command.execute(request));
    }
}