package ten.give.web.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ten.give.web.form.EmailResultForm;
import ten.give.web.service.EmailService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@Api(tags = "EmailController")
public class EmailController {


    private final EmailService emailService;

    @ApiOperation(
            value = "DonorCard 정보 수정하기",
            notes = "Donor Card 정보 수정하기<br>" +
                    "[ EX ] URL : http://localhost:8080/sendmail")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(
                            name = "email",
                            value = "인증 token을 전송할 e-mail 주소",
                            required = true,
                            dataType = "JSON",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @PostMapping("/sendemail")
    public EmailResultForm sendEmail(@RequestBody Map<String,Object> email){

        log.info("toEmail  : {}", (String) email.get("toEmail"));
        String toEmail = (String) email.get("toEmail");

        return emailService.sendEmail(toEmail);

    }
}
