package ten.give.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ten.give.web.form.EmailResultForm;
import ten.give.web.form.MailSendForm;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EmailUtilsImple implements EmailUtils{

    private final JavaMailSender sender;

    private static final String suffixTitle = "님 본인 인증 Mail 입니다.";
    private static final String midBody = "님 의 인증 문자는 [ ";
    private static final String suffixBody = " ] 입니다. 입력창에 입력해 주세요";

    @Value("${email.expireTime}")
    private int expireTime;

    @Override
    public EmailResultForm sendEmail(String toAddress, String subject, String body, EmailResultForm result) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body);
            result.setCreateTime(LocalDateTime.now());
            result.setExpireTime(expireTime);
        } catch (MessagingException e) {
            e.printStackTrace();
            result.setCreateTime(null);
            result.setExpireTime(expireTime);
        }

        sender.send(message);
        return result;
    }

    public MailSendForm setMailSubjectAndBody(String userMail, String authToken) {

        MailSendForm mailSendForm = new MailSendForm();
        mailSendForm.setToEmail(userMail);
        String mail = userMail.substring(0,userMail.indexOf("@"));
        mailSendForm.setSubject(mail + suffixTitle);
        mailSendForm.setBody(mail + midBody + authToken + suffixBody);
        return mailSendForm;


    }

}
