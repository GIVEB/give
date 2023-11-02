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

        //jpaRepository.checkFollow(fromId,toId);

        return false;
    }

    @Override
    public ResultForm Follow(Long fromId, Long toId) {

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
        follow.setUpdatDate(now);

        jpaRepository.save(follow);

        return new ResultForm(true,toUser.get().getName() + "님을 Follow 하였습니다.");
    }

    @Override
    public ResultForm unFollow(Long fromId, Long toId) {



        return null;
    }

    @Override
    public List<User> getFollowings(Long loginId) {
        return null;
    }

    @Override
    public List<User> getFollowers(Long loginId) {
        return null;
    }

    @Override
    public Long getFollowingCount(Long loginId) {
        return null;
    }

    @Override
    public Long getFollowwerCount(Long loginId) {
        return null;
    }
}
