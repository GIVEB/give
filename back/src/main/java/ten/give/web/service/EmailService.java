package ten.give.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ten.give.common.utils.EmailUtilsImple;
import ten.give.common.utils.RandomTokenUtils;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.form.EmailResultForm;
import ten.give.web.form.MailSendForm;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailUtilsImple emailUtil;
    private final RandomTokenUtils randomTokenUtils;

    public EmailResultForm sendEmail(String userId){

        EmailResultForm result = new EmailResultForm();

        String intToken = randomTokenUtils.excuteGenerate();

        result.setToken(intToken);

        MailSendForm mailSendForm = emailUtil.setMailSubjectAndBody(userId, intToken);

        return emailUtil.sendEmail(
                mailSendForm.getToEmail(),
                mailSendForm.getSubject(),
                mailSendForm.getBody(),
                result
        );
    }

}
