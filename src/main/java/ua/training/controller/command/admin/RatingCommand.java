package ua.training.controller.command.admin;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RatingCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/form_applicants_rating.jsp";

    }
}