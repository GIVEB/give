package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ten.give.common.enums.DonorKind;
import ten.give.common.enums.Gender;

import java.time.LocalDate;

@ApiModel(value = "DonorCard 정보")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class DonorCardInfoForm {

    @ApiModelProperty(value="카드 ID", example = "2")
    private Long cardId;
    @ApiModelProperty(value="헌혈 종류", example = "PLASMA", required = true)
    private DonorKind kind;
    @ApiModelProperty(value="생년월일 [년-월-일]", example = "2000-02-11", required = true)
    private LocalDate birth;
    @ApiModelProperty(value="헌혈자 이름", example = "양지웅", required = true)
    private String name;
    @ApiModelProperty(value="성별", example = "M", required = true)
    private Gender gender;
    @ApiModelProperty(value="헌혈 날짜 [년-월-일]", example = "2023-09-01", required = true)
    private LocalDate donorDate;
    @ApiModelProperty(value="혈액원", example = "충청북도", required = true)
    private String donorCenter;
    @ApiModelProperty(value="소유자 ID", example = "3", required = true)
    private Long userId;
    @ApiModelProperty(value="등록 날짜 / 기부된 날짜 [년-월-일]", example = "2023-09-11", required = false)
    private LocalDate registrationDate;

    public DonorCardInfoForm() {
    }
}
