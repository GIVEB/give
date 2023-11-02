package ten.give.domain.entity.repository.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ten.give.domain.entity.user.User;
import ten.give.web.form.UserInfoForm;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImplements implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserJpaQueryRepository queryRepository;

    @Override
    public User saveUser(User user) {
        return jpaRepository.save(user);
    }


    @Override
    @Transactional
    public void updateUser(Long userId, UserInfoForm updateParam) {
        Optional<User> target = jpaRepository.findById(userId);

        log.info("updateParam : {} ", updateParam.getName());

        if (!target.isEmpty()) {
            User targetUser = target.get();
            log.info("targetUser : {} ", targetUser.getName());
            targetUser.setName(updateParam.getName());
            targetUser.setAddress(updateParam.getAddress());
            targetUser.setAddress_detail(updateParam.getAddress_detail());
            targetUser.setPhone(updateParam.getPhone());
            targetUser.setBirth_year(updateParam.getBirth_year());
            targetUser.setBirth_month(updateParam.getBirth_month());
            targetUser.setBirth_day(updateParam.getBirth_day());
        }
    }

    @Override
    public void deleteUserByUserId(Long userId) {
        jpaRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findUserByUserId(Long userId) {
        log.info("userId : [{}]",userId);
        return jpaRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> byEmail = jpaRepository.findByEmail(email);
        if (!byEmail.isEmpty()){
            log.info("{}",byEmail.get().getName());
        }
        return byEmail;
    }

    @Override
    public Long getDonationCount(Long userId) {
        return jpaRepository.getDonationCountByUserId(userId);
    }

    @Override
    public Long getTotalDonationCount() {
        return jpaRepository.getTotalDonationCount();
    }



}
