package ten.give.domain.entity.repository.donorcard;

import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.user.User;
import ten.give.web.form.DonorUpdateForm;

import java.util.List;
import java.util.Optional;

public interface DonorCardRepository {

    DonorCard saveCard(DonorCard card);

    void updateCard(Long cardId, DonorUpdateForm updateParam, User user);

    void deleteCardByCardId(Long cardId);

    Optional<DonorCard> findCardByCardId(Long cardId);

    List<DonorCard> findAll();

    Integer getCardCount(Long userId);

    List<DonorCard> getCardListByUserId(Long userId);
}
