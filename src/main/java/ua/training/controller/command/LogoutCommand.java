package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String userLogin = (String) request.getSession().getAttribute("login");
        CommandUtility.logUserOut(request, userLogin);
        return "/index.jsp";
    }
}
