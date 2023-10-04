package ten.give.common.utils;

import ten.give.web.form.EmailResultForm;
import ten.give.web.form.MailSendForm;

public interface EmailUtils {
    EmailResultForm sendEmail(String toAddress, String subject, String body,EmailResultForm result);

    MailSendForm setMailSubjectAndBody(String name, String token);
}
