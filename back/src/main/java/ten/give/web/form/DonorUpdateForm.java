package ten.give.web.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ten.give.common.enums.DonorKind;
import ten.give.common.enums.Gender;

import java.time.LocalDate;

@ApiModel(value = "Donor Card 수정 form")
@Data
public class DonorUpdateForm {

    @ApiModelProperty(value = "헌혈자 이름", required = true, example = "양지웅")
    private String name;

    @ApiModelProperty(value = "헌혈 종류 [0,1,2]", required = true, example = "PLASMA")
    private DonorKind kind;

    @ApiModelProperty(value = "생년월일 [년-월-일]", required = true, example = "2000-11-30")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @ApiModelProperty(value = "성별", required = true, example = "M")
    private Gender gender;

    @ApiModelProperty(value = "헌혈 날짜 [년-월-일]", required = true, example = "2023-09-11")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate donorDate;

    @ApiModelProperty(value = "혈액원", required = true, example = "충청북도")
    private String donorCenter;

    @ApiModelProperty(value = "변경할 user", required = true, example = "2")
    private Long userId;

}
