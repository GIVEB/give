package ten.give.web.form;

import lombok.Data;

@Data
public class MailSendForm {

    private String toEmail;
    private String subject;
    private String body;

}
