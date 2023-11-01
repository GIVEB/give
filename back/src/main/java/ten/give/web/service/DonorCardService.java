package ten.give.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.repository.donorcard.DonorCardRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.form.DonorAddForm;
import ten.give.web.form.DonorCardInfoForm;
import ten.give.web.form.DonorUpdateForm;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DonorCardService {

    private final DonorCardRepository cardRepository;
    private final UserRepository userRepository;

    public Map<String, List<DonorCardInfoForm>> getCardList(){

        Map<String,List<DonorCardInfoForm>> result = new HashMap<>();

        List<DonorCardInfoForm> cardInfoList = new ArrayList<>();

        cardRepository.findAll().listIterator().forEachRemaining(card -> cardInfoList.add(card.donorCardToDonorCardInfoForm()));

        result.put("donorCard",cardInfoList);

        return result;
    }

    public DonorCardInfoForm getCard(Long cardId) {

        Optional<DonorCard> target = cardRepository.findCardByCardId(cardId);

        if (!target.isEmpty()){
            return target.get().donorCardToDonorCardInfoForm();
        }else {
            throw new NoSuchTargetException("존재하지 않습니다.");
        }

    }

    public DonorCardInfoForm addCard(DonorAddForm form, Authentication authentication) {
        Optional<User> findUser = userRepository.findUserByUserId(Long.valueOf(authentication.getName()));
        if (!findUser.isEmpty()){
            DonorCard donorCard = DonorCard.buildDonorCard(form, findUser.get());
            DonorCard savedCard = cardRepository.saveCard(donorCard);
            return savedCard.donorCardToDonorCardInfoForm();
        }

        throw new NoSuchTargetException("사용자가 존재하지 않습니다.");

    }

    public ResultForm deleteCard(Long cardId) {
        Optional<DonorCard> cardByCardId = cardRepository.findCardByCardId(cardId);
        if (!cardByCardId.isEmpty()){
            cardRepository.deleteCardByCardId(cardId);
            return new ResultForm(true,"삭제 성공");
        }

        return new ResultForm(false,"게시물이 존재하지 않습니다.");

    }

    public ResultForm deleteCardsByUserId(Long userId){
        List<DonorCard> result = cardRepository.getCardListByUserId(userId);

        if (result.isEmpty()){
            return new ResultForm(false, "삭제할 것이 존재하지 않습니다.");
        }

        for (DonorCard card : result) {
            cardRepository.deleteCardByCardId(card.getCardId());
        }

        return new ResultForm(true, "삭제완료");

    }

    public DonorCardInfoForm updateCard(Long cardId, DonorUpdateForm form) {
        Optional<DonorCard> cardByCardId = cardRepository.findCardByCardId(cardId);
        Optional<User> user = userRepository.findUserByUserId(form.getUserId());
        if (!cardByCardId.isEmpty() && !user.isEmpty()){
            cardRepository.updateCard(cardId,form,user.get());
            return cardRepository.findCardByCardId(cardId).get().donorCardToDonorCardInfoForm();
        }
        throw new NoSuchTargetException("존재하지 않는 정보 입니다.");
    }

    public Integer getCardCount(Long userId) {
        return cardRepository.getCardCount(userId);
    }


    public Map<String,List<DonorCardInfoForm>> getCardListByUserId(Long userId) {
        Map<String,List<DonorCardInfoForm>> result = new HashMap<>();

        List<DonorCardInfoForm> cardInfoList = new ArrayList<>();

        cardRepository.getCardListByUserId(userId).stream().iterator().forEachRemaining(card -> cardInfoList.add(card.donorCardToDonorCardInfoForm()));

        result.put("donorCard",cardInfoList);

        return result;
    }

    private void printDonorCard(DonorCard c) {
        log.info("[{}] -> name : {} , kind: {}, birth: {}, gender : {} , donor_date : {} , donor_center : {} , registrationDate : {} , user : {}",
                c.getCardId(), c.getName(), c.getKind(), c.getBirth(),c.getGender(),c.getDonorDate(),c.getDonorCenter(),c.getRegistrationDate(),c.getUser().getUserId());
    }

    @Transactional
    public Map<String,List<DonorCardInfoForm>> DonationCard(Long toId, List<Long> cardIdList, String loginId){

        Map<String,List<DonorCardInfoForm>> result = new HashMap<>();

        List<DonorCardInfoForm> cardInfoList = new ArrayList<>();

        for ( Long cardId : cardIdList) {

            Optional<DonorCard> cardByCardId = cardRepository.findCardByCardId(cardId);
            Optional<User> userByUserId = userRepository.findUserByUserId(toId);

            if (checkSelfDonation(cardByCardId,userByUserId)){
                throw new IllegalArgumentException("자신에게는 기부할 수 없습니다.");
            }

            if(checkRedBoxAccess(cardByCardId,userByUserId)){
                DonorCard cardInfo = cardByCardId.get();

                if (cardByCardId.get().getUser().getUserId() == 1 && !loginId.equals("1")){
                    throw new IllegalArgumentException("RedBox 는 접근 불허");
                }

                if (cardByCardId.get().getUser().getUserId() != Long.valueOf(loginId) && !loginId.equals("1")){
                    throw new IllegalArgumentException("자신의 카드만 기부가능 합니다.");
                }

                DonorUpdateForm form = new DonorUpdateForm().builder().userId(toId)
                        .donorCenter(cardInfo.getDonorCenter())
                        .birth(cardInfo.getBirth())
                        .donorDate(cardInfo.getDonorDate())
                        .kind(cardInfo.getKind())
                        .name(cardInfo.getName())
                        .gender(cardInfo.getGender())
                        .build();

                User loginedUser = userRepository.findUserByUserId(Long.valueOf(loginId)).get();
                loginedUser.setDonationCount(loginedUser.getDonationCount() +1);

                DonorCardInfoForm donorCardInfoForm = updateCard(cardId, form);
                cardInfoList.add(donorCardInfoForm);

            }else{
                throw new NoSuchTargetException("user 나 card 가 존재하지 않습니다.");
            }

        }

        result.put("donorCard",cardInfoList);

        return result;

    }

    private Boolean checkRedBoxAccess(Optional<DonorCard> cardByCardId , Optional<User> userByUserId ){
        return !cardByCardId.isEmpty() && !userByUserId.isEmpty();
    }

    private Boolean checkSelfDonation(Optional<DonorCard> cardByCardId , Optional<User> userByUserId){
        return cardByCardId.get().getUser().getUserId() == userByUserId.get().getUserId();
    }

}
