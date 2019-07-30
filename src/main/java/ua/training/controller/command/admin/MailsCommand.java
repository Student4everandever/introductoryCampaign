package ua.training.controller.command.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
import ua.training.controller.command.Command;
import ua.training.controller.mail.sender.SendMail;
import ua.training.model.entity.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class MailsCommand implements Command {

    private final static Logger logger = LogManager.getLogger(MailsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        int requiredRating = 100;
        String[] to = UserService.getStudents(requiredRating);
        String sendMails = request.getParameter("send_mail");
        String user = "ukrposhta@gmail.com";
        String password = "dikanskaya1";
        String message;

        if (sendMails == null) {
            List<User> usersWithRating = UserService.getUsersWithRating();
            request.setAttribute("users", usersWithRating);
            return "/WEB-INF/admin/send_mails.jsp";
        }

        SendMail sender = new SendMail(user, password);
        String subject = "Introductory campaign notification";
        String text = "Dear Applicant"
                + "\n\nWe are happy to congratulate you with entering the chosen university!";

        sender.send(subject, text, user, to);
/*
        Email email = new SimpleEmail();
        try {
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("ukrposhta@gmail.com", "dikanskaya1"));
            email.setSSLOnConnect(true);
            email.setFrom("ukrposhta@gmail.com");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail ... :-)");
            email.addTo("alexkidov@ukr.net");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
*/

        message = "Email sent successfully";
        request.setAttribute("message", message);
        logger.info(String.format(Messages.ADMIN_MAIL_COMMAND_SENT_SUCCESS,  Arrays.toString(to)));
        return "/campaign/admin/admin_base";
    }
}

