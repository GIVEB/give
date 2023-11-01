package ten.give.domain.entity.repository.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jaxen.expr.iter.IterableAncestorAxis;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.form.UserInfoForm;

import java.util.ArrayList;
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

    @Override
    public List<User> follow(Long loginId, Long followingId) {

        if (checkSameIds(loginId,followingId)){
            throw new IllegalArgumentException("자신을 Follow 할 수 는 없습니다.");
        }

        Optional<User> loginUser = findUserByUserId(loginId);

        if (loginUser.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 로그인 유저 입니다.");
        }

        Optional<User> target = findUserByUserId(followingId);

        if (target.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 Target 입니다.");
        }

        loginUser.get().getFollowing().add(target.get());

        saveUser(loginUser.get());

        return new ArrayList<>(loginUser.get().getFollowing());
    }

    @Override
    public ResultForm deleteFollow(Long loginId, Long unfollowId) {

        if (checkSameIds(loginId,unfollowId)){
            throw new IllegalArgumentException("자신을 UnFollow 할 수 는 없습니다.");
        }

        Optional<User> loginUser = findUserByUserId(loginId);

        if (loginUser.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 로그인 유저 입니다.");
        }

        Optional<User> target = findUserByUserId(unfollowId);

        if (target.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 Target 입니다.");
        }

        loginUser.get().getFollowing().remove(target.get());

        saveUser(loginUser.get());

        return new ResultForm(true, target.get().getName()+"님을 Unfollow 하였습니다.");
    }

    @Override
    public List<User> getFollowings(Long loginId) {
        Optional<User> loginUser = findUserByUserId(loginId);

        if (loginUser.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 로그인 유저 입니다.");
        }

        return loginUser.get().getFollowing();
    }

    @Override
    public List<User> getFollower(Long loginId) {
        return null;
    }

    private boolean checkSameIds(Long loginId, Long followingId){
        return loginId == followingId;
    }

}
