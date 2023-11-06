package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ten.give.common.enums.Grade;
import ten.give.domain.entity.user.Follow;
import ten.give.domain.entity.user.User;

import java.util.ArrayList;
import java.util.List;

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

    @ApiModelProperty(value = "기부 횟수", example = "100")
    private Long donationCount;

    @ApiModelProperty(value = "총 기부 횟수" , example = "303424")
    private Long totalDonationCount;

    @ApiModelProperty(value = "등급", example = "OIL TANK")
    private Grade grade;

    @ApiModelProperty(value = "성별", example = "MALE, FEMALE")
    private String gender;

    @ApiModelProperty(value = "followings" , example = "followings")
    private Long followingCount;

    @ApiModelProperty(value = "followers",example = "followers")
    private Long followerCount;

}
