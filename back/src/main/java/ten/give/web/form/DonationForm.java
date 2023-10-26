package ten.give.web.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "Donor Card 기부 정보")
public class DonationForm {

    @ApiModelProperty(value = "기부 Card Id 목록", required = true, example = "[1,2,3,4,5,...]")
    List<Long> cardIdList = new ArrayList<>();

    @ApiModelProperty(value = "기부 대상", required = true, example = "1")
    Long userId;

}
