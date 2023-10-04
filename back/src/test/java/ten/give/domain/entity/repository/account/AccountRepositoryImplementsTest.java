package ten.give.domain.entity.repository.account;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ten.give.domain.entity.user.Account;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class AccountRepositoryImplementsTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("계정 CRUD TEST")
    void crud(){

        Account account =  new Account().builder().email("testshin@test.com").password("test").build();

        Account savedAccount = accountRepository.saveAccount(account);

        log.info("[{}] email : {} , password : {}" , savedAccount.getAccountId(),savedAccount.getEmail(),savedAccount.getPassword());
        assertThat(savedAccount.getEmail()).isEqualTo("testshin@test.com");

        Optional<Account> target = accountRepository.findAccountByAccountId(3L);

        if (!target.isEmpty()){
            log.info("[{}] email : {} , password : {}" , target.get().getAccountId(),target.get().getEmail(),target.get().getPassword());
            assertThat(target.get().getAccountId()).isEqualTo(savedAccount.getAccountId());
        }

        accountRepository.deleteAccountByAccountId(3L);

        target = accountRepository.findAccountByAccountId(3L);

        assertThat(target).isEmpty();

        List<Account> result = accountRepository.findAll();

        for ( Account a : result ) {
            log.info("[{}] email = {} , password = {}", a.getAccountId() , a.getEmail() , a.getPassword());
        }

        Optional<Account> findEmail = accountRepository.findAccountByEmail("testA@test.com");

        if (!findEmail.isEmpty()){
            Account getAccount = findEmail.get();
            log.info("[{}] email = {} . password = {}",getAccount.getAccountId(), getAccount.getEmail(),getAccount.getPassword());
        }

    }



}