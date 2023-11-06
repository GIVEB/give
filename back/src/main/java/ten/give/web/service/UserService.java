package ten.give.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ten.give.common.utils.RandomTokenUtils;
import ten.give.domain.entity.repository.account.AccountRepository;
import ten.give.domain.entity.repository.user.FollowRepository;
import ten.give.domain.entity.repository.user.UserRepository;
import ten.give.domain.entity.user.Account;
import ten.give.domain.entity.user.Follow;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.form.JoinForm;
import ten.give.web.form.UserInfoForm;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final DonorCardService donorCardService;
    private final FollowRepository followRepository;

    public ResultForm joinUser(JoinForm form) {

        if (!emailCheck(form)){
            return new ResultForm(false,"이미 회원가입 되어 있습니다.");
        }

        if(!buildAccountAndUser(form)){
            throw new IllegalStateException("가입 중 오류가 발생 했습니다.");
        }

        return  new ResultForm(true, "가입 완료.");

    }

    @Transactional
    public ResultForm withdrawal(Long userId) {

        Optional<User> userByUserId = userRepository.findUserByUserId(userId);

        if(userByUserId.isEmpty()){
            return  new ResultForm(false, "존재하지 않습니다.");
        }

        Optional<Account> accountByAccountId = accountRepository.findAccountByAccountId(userByUserId.get().getAccount().getAccountId());

        if (accountByAccountId.isEmpty()){
            throw new IllegalStateException("회원 조회 이상");
        }

        donorCardService.deleteCardsByUserId(userId);

        userRepository.deleteUserByUserId(userId);
        accountRepository.deleteAccountByAccountId(accountByAccountId.get().getAccountId());

        return new ResultForm(true,"회원 탈퇴가 완료 되었습니다.");

    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> getuserByAccountId(Long userId){return userRepository.findUserByUserId(userId);}

    private boolean buildAccountAndUser(JoinForm form) {
        Account account = new Account().builder()
                .email(form.getEmail())
                .password(form.getPassword()).build();

        User user = new User().builder()
                .name(form.getName())
                .account(account)
                .Address(form.getAddress())
                .Address_detail(form.getAddressDetail())
                .phone(form.getPhone())
                .birth_year(form.getBirthYear())
                .birth_month(form.getBirthDay())
                .birth_day(form.getBirthDay())
                .joinDate(LocalDate.now())
                .donationCount(0L)
                .gender(form.getGender())
                .build();

        Account savedAccount = accountRepository.saveAccount(account);
        User savedUser = userRepository.saveUser(user);

        return (savedUser != null) && (savedAccount != null);
    }

    private boolean emailCheck(JoinForm form) {
        Optional<Account> accountByEmail = accountRepository.findAccountByEmail(form.getEmail());
        if (accountByEmail.isEmpty()){
            return true;
        }

        return false;
    }

    @Transactional
    public UserInfoForm editUserInfo(Long userId, UserInfoForm updateparam) {

        Optional<User> userByUserId = userRepository.findUserByUserId(userId);

        if (userByUserId.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 User 입니다.");
        }

        userRepository.updateUser(userId,updateparam);

        Optional<User> updatedUser = userRepository.findUserByUserId(userId);
        Long totalDonationCount = getTotalDonationCount();

        Long followingCount = followRepository.getFollowingCount(userId);
        Long followerCount = followRepository.getFollowerCount(userId);

        UserInfoForm userInfo = updatedUser.get().userTransferToUserInfo(totalDonationCount,followingCount,followerCount);

        return userInfo;
    }

    public Long getTotalDonationCount() {
        return userRepository.getTotalDonationCount();
    }


    public ResultForm findEmail(String name, String phoneNumber) {

        User user = userRepository.findUserByNameAndPhoneNumber(name,phoneNumber);

        if (user == null){
            throw new NoSuchTargetException("존재하지 않는 회원입니다. [find email]");
        }

        return new ResultForm(true,user.getAccount().getEmail());
    }

    public ResultForm findPassword(String name, String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()){
            throw new NoSuchTargetException("존재하지 않는 회원입니다. [find email]");
        }

        User target = user.get();

        if(!target.getName().equals(name)){
            throw new NoSuchTargetException("존재하지 않는 회원입니다. [find email not equals name]");
        }

        String randomToken = RandomTokenUtils.excuteGenerate();
        target.getAccount().setPassword(randomToken);
        userRepository.saveUser(target);

        return new ResultForm(true,target.getAccount().getPassword());
    }

    public ResultForm editPassword(Long userId, String password) {

        Optional<User> userByUserId = userRepository.findUserByUserId(userId);

        if (userByUserId.isEmpty()){
            throw new NoSuchTargetException("로그인 필요 [ edit Password ]");
        }

        User target = userByUserId.get();

        target.getAccount().setPassword(password);

        userRepository.saveUser(target);

        return new ResultForm(true,"정상적으로 password 가 변경 되었습니다.");
    }


}
