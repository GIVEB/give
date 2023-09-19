package ten.give.domain.entity.repository.donorcard;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ten.give.common.enums.DonorKind;
import ten.give.common.enums.Gender;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static ten.give.common.enums.DonorKind.*;
import static ten.give.common.enums.Gender.*;

@Slf4j
@SpringBootTest
class DonorCardRepositoryImplementsTest {

    @Autowired
    DonorCardRepository donorRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void crud(){

        User user = new User().builder().name("test").email("test@test.com").password("test").build();

        userRepository.saveUser(user);

        DonorCard donorCard = new DonorCard().builder()
                .name("shin")
                .gender(M)
                .birth(LocalDate.of(1994,02,11))
                .donorDate(LocalDate.of(2023,9,11))
                .donorCenter("seoul")
                .kind(WHOLE)
                .user(user)
                .build();

        DonorCard cardA = donorRepository.saveCard(donorCard);

        assertThat(cardA.getCardId()).isEqualTo(1L);

        Optional<DonorCard> findCard = donorRepository.findCardByCardId(1L);

        if (!findCard.isEmpty()){
            DonorCard finedCard = findCard.get();
            log.info("[{}] name : {} , gender : {}, birth : {} , donor_date : {} , donor_center : {} , kind : {} , user : {} "
                    ,finedCard.getCardId(),finedCard.getName(),finedCard.getGender(),finedCard.getBirth(),finedCard.getDonorDate(),
                    finedCard.getDonorCenter(),finedCard.getKind(),finedCard.getUser().getUserId());
            assertThat(finedCard.getCardId()).isEqualTo(1L);
        }

        donorRepository.deleteCardByCardId(1L);

        DonorCard donorCardB = new DonorCard().builder()
                .name("shin test")
                .gender(M)
                .birth(LocalDate.of(1994,02,11))
                .donorDate(LocalDate.of(2023,9,11))
                .donorCenter("seoul test")
                .kind(PLATELETS)
                .user(user)
                .build();
        DonorCard cardB = donorRepository.saveCard(donorCardB);

        DonorCard donorCardC = new DonorCard().builder()
                .name("shin testB")
                .gender(F)
                .birth(LocalDate.of(1994,02,11))
                .donorDate(LocalDate.of(2023,9,11))
                .donorCenter("seoul testB")
                .kind(PLASMA)
                .user(user)
                .build();
        DonorCard cardC = donorRepository.saveCard(donorCardC);

        List<DonorCard> result = donorRepository.findAll();

        for ( DonorCard d : result) {
            log.info("[{}] name : {} , gender : {}, birth : {} , donor_date : {} , donor_center : {} , kind : {} , user : {} "
                    ,d.getCardId(),d.getName(),d.getGender(),d.getBirth(),d.getDonorDate(),
                    d.getDonorCenter(),d.getKind(),d.getUser().getUserId());
        }

    }



}