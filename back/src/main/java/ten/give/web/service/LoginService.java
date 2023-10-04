package ten.give.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ten.give.common.utils.JwtUtils;
import ten.give.domain.entity.repository.account.AccountRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.Account;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public String login(String email, String pw){

        Account account = accountRepository.findAccountByEmail(email).filter(u -> u.getPassword().equals(pw)).orElse(null);
        if (account == null){
            throw new NoSuchTargetException("존재하지 않은 유저 입니다.");
        }

        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail.isEmpty()){
            throw new NoSuchTargetException("존재하지 않은 유저 입니다.");
        }

        Long userId = userByEmail.get().getUserId();
        log.info("userId : {} ", userId);

        return JwtUtils.createJwt(userId,secretKey,expiredMs);
    }

}
