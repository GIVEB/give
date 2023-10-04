package ten.give.domain.entity.repository.donorcard;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ten.give.common.enums.DonorCenter;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.repository.account.AccountRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.Account;
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
    @Autowired
    AccountRepository accountRepository;

    @Test
    void crud(){
        Account account = new Account().builder()
                .email("test@gmail.com").password("test").build();
        accountRepository.saveAccount(account);
        User user = new User().builder()
                .account(account)
                .name("test")
                .phone("010-xxx-xxx")
                .Address("chungju")
                .Address_detail("KNUT")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .build();
        userRepository.saveUser(user);

        DonorCard donorCard = new DonorCard().builder()
                .name("shin")
                .gender(M)
                .birth(LocalDate.of(1994,02,11))
                .donorDate(LocalDate.of(2023,9,11))
                .donorCenter(DonorCenter.CHUNGNAM)
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
                .donorCenter(DonorCenter.CHUNGNAM)
                .kind(PLATELETS)
                .user(user)
                .build();
        DonorCard cardB = donorRepository.saveCard(donorCardB);

        DonorCard donorCardC = new DonorCard().builder()
                .name("shin testB")
                .gender(F)
                .birth(LocalDate.of(1994,02,11))
                .donorDate(LocalDate.of(2023,9,11))
                .donorCenter(DonorCenter.GYEONGBUK)
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