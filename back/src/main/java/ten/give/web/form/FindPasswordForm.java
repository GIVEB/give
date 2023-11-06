package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "Email 찾기 입력 Form")
public class FindPasswordForm {

    @NotEmpty
    @ApiModelProperty(value = "이름", example = "신영운")
    private String name;
    @NotEmpty
    @ApiModelProperty(value = "메일 주소", example = "test@gmail.com")
    private String email;

}
