package ten.give.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.repository.donorcard.DonorCardRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.DeleteResult;
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

    public DonorCardInfoForm addCard(DonorAddForm form) {
        Optional<User> findUser = userRepository.findUserByUserId(form.getUserId());
        if (!findUser.isEmpty()){
            DonorCard donorCard = DonorCard.buildDonorCard(form, findUser.get());
            log.info("{}" ,donorCard.getName());
            DonorCard savedCard = cardRepository.saveCard(donorCard);
            return savedCard.donorCardToDonorCardInfoForm();
        }

        throw new NoSuchTargetException("사용자가 존재하지 않습니다.");

    }

    public DeleteResult deleteCard(Long cardId) {
        Optional<DonorCard> cardByCardId = cardRepository.findCardByCardId(cardId);
        if (!cardByCardId.isEmpty()){
            cardRepository.deleteCardByCardId(cardId);
            return new DeleteResult(true);
        }

        throw new NoSuchTargetException("게시물이 존재하지 않습니다.");

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



}
