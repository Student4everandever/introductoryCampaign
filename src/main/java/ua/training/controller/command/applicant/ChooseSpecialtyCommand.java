package ua.training.controller.command.applicant;

import ua.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ChooseSpecialtyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/applicant/choose_specialty.jsp";
    }
}
