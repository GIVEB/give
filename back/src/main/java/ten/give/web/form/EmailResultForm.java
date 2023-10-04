package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "Email 송부 완료 후 결과")
public class EmailResultForm {

    @ApiModelProperty(value = "email 인증 용 4자리 token", required = true, example = "1234")
    private String token;
    @ApiModelProperty(value = "token 밸행 시간", required = true, example = "2023-10-04T17:36:51.202726")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "token 만료 기간", required = true, example = "180")
    private Integer expireTime;

    public EmailResultForm() {
    }

    public EmailResultForm(String token, LocalDateTime createTime, Integer expireTime) {
        this.token = token;
        this.createTime = createTime;
        this.expireTime = expireTime;
    }
}
