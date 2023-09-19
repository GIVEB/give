package ten.give.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ten.give.common.enums.DonorKind;
import ten.give.domain.entity.donorcard.DonorCard;
import ten.give.domain.entity.repository.donorcard.DonorCardRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.User;
import ten.give.common.enums.Gender;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final DonorCardRepository cardRepository;

    @PostConstruct
    public void InitUserData(){
            User user = new User().builder().name("testA").email("testA@test.com").password("testA").build();
            userRepository.saveUser(user);
    }

    @PostConstruct
    public void InitDonorCardData(){
        User user = new User().builder().name("test").email("test@test.com").password("test").build();
        userRepository.saveUser(user);
        for (int i = 1; i <= 10; i++){
            DonorCard donorCard = new DonorCard().builder()
                    .name("test" + 1)
                    .gender(Gender.M)
                    .birth(LocalDate.of(1994,02,11))
                    .donorDate(LocalDate.of(2023,9,11))
                    .donorCenter("seoul")
                    .kind(DonorKind.WHOLE)
                    .user(user).build();
            cardRepository.saveCard(donorCard);
        }

    }

}
