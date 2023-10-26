package ten.give.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ten.give.web.form.DonationForm;
import ten.give.web.form.DonorCardInfoForm;
import ten.give.web.service.DonorCardService;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "DonorCardController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/give")
public class GiveController {

    private final DonorCardService cardService;

    @ApiOperation(
            value = "DonorCard Donation To Redbox",
            notes = "Redbox 로 기부하기 <br>" +
                    "[ EX ] URL : http://localhost:8080/give" +
                    "관리자 계정 외 red box 내 card 접근 시 404 Exception 발생" +
                    "그외 card 는 관리자 외 접근 가능")
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @PostMapping
    public Map<String, List<DonorCardInfoForm>> getList(@RequestBody DonationForm form, Authentication authentication){

        Map<String, List<DonorCardInfoForm>> result = cardService.DonationCard(form.getUserId(), form.getCardIdList(),authentication.getName());

        return result;

    }

}
