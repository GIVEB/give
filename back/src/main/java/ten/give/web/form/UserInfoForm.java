package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "로그인 입력 form")
public class UserInfoForm {

    @ApiModelProperty(value = "사용자 이름", required = true, example = "김실험")
    private String name;

    @ApiModelProperty(value = "생년", example = "2023")
    private String birth_year;

    @ApiModelProperty(value = "생월", example = "01")
    private String birth_month;

    @ApiModelProperty(value = "생일", example = "11")
    private String birth_day;

    @ApiModelProperty(value = "핸드폰번호", example = "01012345678")
    private String phone;

    @ApiModelProperty(value = "주소", example = "청주시 흥덕구 증안로 21")
    private String Address;

    @ApiModelProperty(value = "주소 상세", example = "xx 아파트, xxx동 xxx호")
    private String Address_detail;


}
