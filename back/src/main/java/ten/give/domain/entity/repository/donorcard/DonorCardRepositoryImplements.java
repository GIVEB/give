package ten.give.domain.entity.repository.donorcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.user.User;
import ten.give.web.form.DonorUpdateForm;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DonorCardRepositoryImplements implements DonorCardRepository{

    private final DonorCardJpaRepository jpaRepository;
    private final DonorCardJpaQueryRepository queryRepository;

    @Override
    public DonorCard saveCard(DonorCard card) {
        return jpaRepository.save(card);
    }

    @Override
    public void updateCard(Long cardId, DonorUpdateForm updateParam, User user) {

        Optional<DonorCard> findCard = jpaRepository.findById(cardId);

        if (!findCard.isEmpty()){

            DonorCard donorCard = findCard.get();
            donorCard.setName(updateParam.getName());
            donorCard.setKind(updateParam.getKind());
            donorCard.setDonorCenter(updateParam.getDonorCenter());
            donorCard.setBirth(updateParam.getBirth());
            donorCard.setDonorDate(updateParam.getDonorDate());
            donorCard.setGender(updateParam.getGender());
            donorCard.setUser(user);

        }

    }

    @Override
    public void deleteCardByCardId(Long cardId) {
        jpaRepository.deleteById(cardId);
    }

    @Override
    public Optional<DonorCard> findCardByCardId(Long cardId) {
        return jpaRepository.findById(cardId);
    }

    @Override
    public List<DonorCard> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Integer getCardCount(Long userId) {
        return jpaRepository.countByUser_UserId(userId);
    }

    @Override
    public List<DonorCard> getCardListByUserId(Long userId) {
        return queryRepository.findAll(userId);
    }
}
