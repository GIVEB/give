package ten.give.domain.entity.repository.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ten.give.domain.entity.repository.account.AccountRepository;
import ten.give.domain.entity.user.Account;
import ten.give.domain.entity.user.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Slf4j
class UserRepositoryImplementsTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    void save(){

        Account account = new Account().builder()
                .email("testA@gmail.com").password("test").build();
        accountRepository.saveAccount(account);
        User userA = new User().builder()
                .account(account)
                .name("test")
                .phone("010-xxx-xxx")
                .Address("chungju")
                .Address_detail("KNUT")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .build();
        userRepository.saveUser(userA);

        User savedUser = userRepository.saveUser(userA);

        assertThat(savedUser).isEqualTo(userA);

        Optional<User> findUser = userRepository.findUserByUserId(userA.getUserId());
        assertThat(findUser.get().getUserId()).isEqualTo(userA.getUserId());

        userRepository.deleteUserByUserId(userA.getUserId());

        Optional<User> target = userRepository.findUserByUserId(userA.getUserId());
        assertThat(target).isNotPresent();

        Account accountB = new Account().builder()
                .email("testB@gmail.com").password("test").build();
        accountRepository.saveAccount(accountB);
        User userB = new User().builder()
                .account(accountB)
                .name("test")
                .phone("010-xxx-xxx")
                .Address("chungju")
                .Address_detail("KNUT")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .build();
        userRepository.saveUser(userB);

        Account accountC = new Account().builder()
                .email("testC@gmail.com").password("test").build();
        accountRepository.saveAccount(accountC);

        User userC = new User().builder()
                .account(accountC)
                .name("test")
                .phone("010-xxx-xxx")
                .Address("chungju")
                .Address_detail("KNUT")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .build();
        userRepository.saveUser(userC);


        Account accountD = new Account().builder()
                .email("testD@gmail.com").password("test").build();
        accountRepository.saveAccount(accountD);
        User userD = new User().builder()
                .account(accountD)
                .name("test")
                .phone("010-xxx-xxx")
                .Address("chungju")
                .Address_detail("KNUT")
                .birth_year("1994")
                .birth_month("02")
                .birth_day("11")
                .build();
        userRepository.saveUser(userD);

        User user = userRepository.saveUser(userB);
        userRepository.saveUser(userC);
        userRepository.saveUser(userD);

        List<User> result = userRepository.findAll();

        for ( User u: result) {
            log.info("[{}] name : {} , email : {}, password : {}",u.getUserId(), u.getName(),u.getAccount().getEmail(),u.getAccount().getPassword());
        }

        assertThat(result.size()).isEqualTo(3);

        Optional<User> findEmail = userRepository.findUserByEmail("testB@test.com");

        if (!findEmail.isEmpty()){
            User u = findEmail.get();
            log.info("findEmail : [{}] name : {}, email : {} , password : {}",u.getUserId(),u.getName(),u.getAccount().getEmail(),u.getAccount().getPassword());
            assertThat(u.getAccount().getEmail()).isEqualTo("testB@test.com");
        }





    }


}