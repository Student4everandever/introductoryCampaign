package ua.training.controller.command.admin;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to resolve admin base page request
 */
public class AdminCommand implements Command {

    /**
     * Returns string with path to servlet for admin base page
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/admin/admin_base.jsp";
    }
}
