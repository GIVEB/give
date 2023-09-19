package ten.give.web.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ten.give.domain.exception.form.DeleteResult;
import ten.give.web.form.DonorAddForm;
import ten.give.web.form.DonorCardInfoForm;
import ten.give.web.form.DonorUpdateForm;
import ten.give.web.service.DonorCardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "DonorCardController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/donorcards")
public class DonorCardController {

    private final DonorCardService cardService;

    @ApiOperation(
            value = "DonorCard List 조회하기",
            notes = "모든 Donor Card 조회하기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards")
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @GetMapping
    public Map<String,List<DonorCardInfoForm>> getList(){
        Map<String,List<DonorCardInfoForm>> result = cardService.getCardList();
        return result;
    }

    @ApiOperation(
            value = "card_id로 DonorCard 단일 조회하기",
            notes = "card_id로 Donor Card 조회하기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards/2<br>" +
                    "1 : red box 2 ~ : user_id")
    @ApiImplicitParam(
            name = "cardId",
            value = "카드 번호",
            required = true,
            dataType = "Long",
            paramType = "path",
            defaultValue = "None"
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
            @ApiResponse(code=400, message="존재하지 않습니다.")
    })
    @GetMapping("/{cardId}")
    public DonorCardInfoForm getCard(@PathVariable Long cardId){
        return cardService.getCard(cardId);
    }

    @ApiOperation(
            value = "DonorCard 등록하기",
            notes = "Donor Card 정보 등록하기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards")
    @ApiImplicitParam(
            name = "form",
            value = "donor card 정보",
            required = true,
            dataType = "DonorAddForm",
            paramType = "body",
            defaultValue = "None"
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @PostMapping
    public DonorCardInfoForm addCard(@RequestBody DonorAddForm form){
        return cardService.addCard(form);
    }

    @ApiOperation(
            value = "DonorCard 삭제하기",
            notes = "card_id로 Donor Card 삭제하기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards/2")
    @ApiImplicitParam(
            name = "card_id",
            value = "card id",
            required = true,
            dataType = "Long",
            paramType = "path",
            defaultValue = "None"
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @DeleteMapping("/{cardId}")
    public DeleteResult deleteCard(@PathVariable Long cardId){
       return cardService.deleteCard(cardId);
    }

    @ApiOperation(
            value = "DonorCard 정보 수정하기",
            notes = "Donor Card 정보 수정하기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards/3")
    @ApiImplicitParams(
            value = {
                @ApiImplicitParam(
                        name = "cardId",
                        value = "card Id",
                        required = true,
                        dataType = "Long",
                        paramType = "path",
                        defaultValue = "None"
                ),
                    @ApiImplicitParam(
                            name = "form",
                            value = "donor update Parameter",
                            required = true,
                            dataType = "DonorUpdateForm",
                            paramType = "body",
                            defaultValue = "None"
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
            @ApiResponse(code = 400,message = "존재 하지 않습니다.")
    })
    @PatchMapping("/{cardId}")
    public DonorCardInfoForm updateCard(@PathVariable Long cardId, @RequestBody DonorUpdateForm form){
        return cardService.updateCard(cardId,form);
    }

    @ApiOperation(
            value = "DonorCard 수량 보기",
            notes = "user_id [소유자 ID] 로 Donor Card 수량하기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards/count/3")
    @ApiImplicitParam(
            name = "user_id",
            value = "소유자 id",
            required = true,
            dataType = "Long",
            paramType = "path",
            defaultValue = "None"
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @GetMapping("/count/{userId}")
    public Map<String,Integer> cardCount(@PathVariable Long userId){

        Map<String, Integer> result = new HashMap<>();
        Integer cardCount = cardService.getCardCount(userId);
        log.info("count : {}",cardCount);
        result.put("count",cardCount);

        return result;
    }

    @ApiOperation(
            value = "user_id로 DonorCard list 보기",
            notes = "user_id [소유자 ID] 로 Donor Card list 보기<br>" +
                    "[ EX ] URL : http://localhost:8080/donorcards/list/3")
    @ApiImplicitParam(
            name = "user_id",
            value = "소유자 id",
            required = true,
            dataType = "Long",
            paramType = "path",
            defaultValue = "None"
    )
    @ApiResponses({
            @ApiResponse(code=200, message="성공")
    })
    @GetMapping("/list/{userId}")
    public Map<String,List<DonorCardInfoForm>> getCardListByUserId(@PathVariable Long userId){
        Map<String,List<DonorCardInfoForm>> result = cardService.getCardListByUserId(userId);
        return result;
    }

}
