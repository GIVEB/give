package ten.give.domain.entity.repository.account;

import ten.give.domain.entity.user.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account saveAccount(Account Account);

    void updateAccount(Long accountId, Account updateParam);

    void deleteAccountByAccountId(Long accountId);

    Optional<Account> findAccountByAccountId(Long accountId);

    List<Account> findAll();

    Optional<Account> findAccountByEmail(String email);

}
