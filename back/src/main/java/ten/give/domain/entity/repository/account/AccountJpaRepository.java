package ten.give.domain.entity.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import ten.give.domain.entity.user.Account;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account,Long> {

    Optional<Account> findAccountByAccountId(Long accountId);

    Optional<Account> findAccountByEmail(String email);
}
