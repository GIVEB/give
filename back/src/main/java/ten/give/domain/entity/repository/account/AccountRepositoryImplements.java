package ten.give.domain.entity.repository.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ten.give.domain.entity.user.Account;
import ten.give.domain.exception.NoSuchTargetException;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImplements implements AccountRepository{

    private final AccountJpaQueryRepository queryRepository;
    private final AccountJpaRepository jpaRepository;

    @Override
    public Account saveAccount(Account account) {
        return jpaRepository.save(account);
    }

    @Override
    public void updateAccount(Long accountId, Account updateParam) {
        Optional<Account> target = jpaRepository.findAccountByAccountId(accountId);

        if (target.isEmpty()){
            throw new NoSuchTargetException("계정이 존재하지 않습니다.");
        }

        target.get().setEmail(updateParam.getEmail());
        target.get().setPassword(updateParam.getPassword());
    }

    @Override
    public void deleteAccountByAccountId(Long accountId) {
        jpaRepository.deleteById(accountId);
    }

    @Override
    public Optional<Account> findAccountByAccountId(Long accountId) {
        return jpaRepository.findAccountByAccountId(accountId);
    }

    @Override
    public List<Account> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Account> findAccountByEmail(String email) {
        return jpaRepository.findAccountByEmail(email);
    }
}
