package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "로그인 입력 form")
public class LoginForm {

    @NotEmpty
    @ApiModelProperty(value = "로그인 email", required = true, example = "test@test.com")
    private String loginEmail;

    @NotEmpty
    @ApiModelProperty(value = "로그인 password", required = true, example = "test!")
    private String loginPassword;

}
