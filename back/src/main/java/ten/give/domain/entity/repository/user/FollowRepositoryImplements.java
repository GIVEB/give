package ten.give.domain.entity.repository.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ten.give.domain.entity.user.Follow;
import ten.give.domain.entity.user.User;
import ten.give.domain.exception.NoSuchTargetException;
import ten.give.domain.exception.form.ResultForm;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FollowRepositoryImplements implements FollowRepository{

    private final FollowJpaRepository jpaRepository;
    private final FollowJpaQueryRepository queryRepository;
    private final UserRepository userRepository;

    @Override
    public boolean checkFollow(Long fromId, Long toId) {

        if(jpaRepository.checkFollow(fromId,toId).isEmpty()){
            return false;
        }

        return true;
    }

    @Override
    public ResultForm follow(Long fromId, Long toId) {

        Optional<User> fromUser = userRepository.findUserByUserId(fromId);

        if (fromUser.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 유저 : [ fromId ]");
        }

        Optional<User> toUser = userRepository.findUserByUserId(toId);

        if (toUser.isEmpty()){
            throw new NoSuchTargetException("존재 하지 않는 유저 : [ toId ]");
        }

        Follow follow = new Follow();
        follow.setToUser(toUser.get());
        follow.setFromUser(fromUser.get());
        LocalDate now = LocalDate.now();
        follow.setCreateDate(now);
        follow.setUpdateDate(now);

        jpaRepository.save(follow);

        return new ResultForm(true,toUser.get().getName() + "님을 Follow 하였습니다.");
    }

    @Override
    public ResultForm unFollow(Long fromId, Long toId) {

        Optional<Follow> target = jpaRepository.checkFollow(fromId,toId);

        if (target.isEmpty()){
            throw new NoSuchTargetException("Follow 관계가 아닙니다.");
        }

        jpaRepository.deleteFollow(fromId,toId);

        return new ResultForm(true,target.get().getToUser().getName() + "님을 unFollow 하였습니다.");
    }

    @Override
    public List<Follow> getFollowings(Long loginId) {
        return jpaRepository.findAllByFromId(loginId);
    }

    @Override
    public List<Follow> getFollowers(Long loginId) {
        return jpaRepository.findAllByToId(loginId);
    }

    @Override
    public Long getFollowingCount(Long loginId) {
        return jpaRepository.countFollowing(loginId);
    }

    @Override
    public Long getFollowerCount(Long loginId) {
        return jpaRepository.countFollower(loginId);
    }
}
