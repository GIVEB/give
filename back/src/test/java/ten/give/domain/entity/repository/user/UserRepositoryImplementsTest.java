package ten.give.domain.entity.repository.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ten.give.domain.entity.user.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Slf4j
class UserRepositoryImplementsTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save(){

        User userA = new User().builder()
                .name("test")
                .email("test@test.com")
                .password("test")
                .build();

        User savedUser = userRepository.saveUser(userA);

        assertThat(savedUser).isEqualTo(userA);

        Optional<User> findUser = userRepository.findUserByUserId(userA.getUserId());
        assertThat(findUser.get().getUserId()).isEqualTo(userA.getUserId());

        userRepository.deleteUserByUserId(userA.getUserId());

        Optional<User> target = userRepository.findUserByUserId(userA.getUserId());
        assertThat(target).isNotPresent();

        User userB = new User().builder()
                .name("testB")
                .email("testB@test.com")
                .password("test")
                .build();
        User userC = new User().builder()
                .name("testC")
                .email("testC@test.com")
                .password("test")
                .build();
        User userD = new User().builder()
                .name("testD")
                .email("testD@test.com")
                .password("test")
                .build();

        User user = userRepository.saveUser(userB);
        userRepository.saveUser(userC);
        userRepository.saveUser(userD);

        List<User> result = userRepository.findAll();

        for ( User u: result) {
            log.info("[{}] name : {} , email : {}, password : {}",u.getUserId(), u.getName(),u.getEmail(),u.getPassword());
        }

        assertThat(result.size()).isEqualTo(3);

        Optional<User> findEmail = userRepository.findUserByEmail("testB@test.com");

        if (!findEmail.isEmpty()){
            User u = findEmail.get();
            log.info("findEmail : [{}] name : {}, email : {} , password : {}",u.getUserId(),u.getName(),u.getEmail(),u.getPassword());
            assertThat(u.getEmail()).isEqualTo("testB@test.com");
        }





    }


}