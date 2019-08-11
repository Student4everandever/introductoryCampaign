package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
import ua.training.constants.WebPagesMessages;
import ua.training.controller.command.Command;
import ua.training.controller.mail.sender.SendMail;
import ua.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Class to resolve mail sending request
 */

public class MailsCommand implements Command {

    private final static Logger logger = LogManager.getLogger(MailsCommand.class);

    /**
     * Returns string with path to servlet for sending mails page and stores data in request for jsp
     * @param request HttpServletRequest with data from jsp
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {

        int requiredRating = 100;
        String[] to = userService.getStudentsMails(requiredRating);
        String sendMails = request.getParameter("send_mail");
        String user = "ukrposhta@gmail.com";
        String password = "dikanskaya1";

        if (sendMails == null) {
            List<User> usersWithRating = userService.getUsersWithRating();
            request.setAttribute("users", usersWithRating);
            return "/WEB-INF/admin/send_mails.jsp";
        }

        SendMail sender = new SendMail(user, password);
        String subject = "Introductory campaign notification";
        String text = "Dear Applicant"
                + "\n\nWe are happy to congratulate you with entering the chosen university!";

        sender.send(subject, text, user, to);

        request.setAttribute("message", WebPagesMessages.EMAIL_SENT);
        logger.info(String.format(LoggerMessages.ADMIN_MAIL_COMMAND_SENT_SUCCESS,  Arrays.toString(to)));
        return "/campaign/admin/admin_base";
    }
}

