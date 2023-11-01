package ten.give.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ten.give.common.enums.DonorKind;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.repository.account.AccountRepository;
import ten.give.domain.entity.repository.donorcard.DonorCardRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.Account;
import ten.give.domain.entity.user.User;
import ten.give.common.enums.Gender;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

import static ten.give.common.enums.DonorCenter.*;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final DonorCardRepository cardRepository;
    private final AccountRepository accountRepository;

    @PostConstruct
    public void InitUserData() {

        Account adminAccount = new Account().builder().email("admin@gmail.com").password("admin").build();
        accountRepository.saveAccount(adminAccount);

        User adminUser = new User().builder()
                .account(adminAccount)
                .name("관리자")
                .phone("010-1234-1234")
                .Address("chungju")
                .Address_detail("KNUT")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .donationCount(180L)
                .gender(Gender.M)
                .build();
        userRepository.saveUser(adminUser);

        adminUser.setUserId(1L);

        Account account = new Account().builder().email("testA@test.com").password("testA").build();
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
                .donationCount(20L)
                .gender(Gender.F)
                .build();
        userRepository.saveUser(user);



    }

    @PostConstruct
    public void InitDonorCardData() {
        Account accountShin = new Account().builder().email("duddns119@gmail.com").password("tlsduddns123").build();
        accountRepository.saveAccount(accountShin);
        User userShin = new User().builder()
                .account(accountShin)
                .name("신영운")
                .phone("010-4829-5984")
                .Address("청주시 흥덕구 증안로 21")
                .Address_detail("아름다운나날 1차")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .donationCount(200L)
                .gender(Gender.M)
                .build();
        userRepository.saveUser(userShin);
        for (int i = 1; i <= 10; i++) {
            DonorCard donorCard = new DonorCard().builder()
                    .name("test" + 1)
                    .gender(Gender.M)
                    .birth(LocalDate.of(1994, 02, 11))
                    .donorDate(LocalDate.of(2023, 9, 11))
                    .donorCenter(CHUNGNAM)
                    .kind(DonorKind.WHOLE)
                    .user(userShin).build();
            cardRepository.saveCard(donorCard);
        }

    }

}
