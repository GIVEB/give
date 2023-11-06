package ten.give.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ten.give.domain.entity.repository.user.FollowRepository;
import ten.give.domain.entity.user.Follow;
import ten.give.domain.exception.form.ResultForm;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public ResultForm follow(Long fromId, Long toId){

        if (fromId == toId){
            throw new IllegalStateException("자신은 follow 할 수 없습니다.");
        }

        boolean followCheck = followRepository.checkFollow(fromId, toId);

        if (followCheck){
            throw new IllegalStateException("이미 follow 상태 입니다.");
        }

        return followRepository.follow(fromId, toId);

    }

    @Transactional
    public ResultForm unfollow(Long userId, Long followId) {
        if (userId == followId){
            throw new IllegalStateException("자신은 follow 할 수 없습니다.");
        }

        boolean followCheck = followRepository.checkFollow(userId, followId);

        if (!followCheck){
            throw new IllegalStateException("follow 상태가 아닙니다.");
        }

        return followRepository.unFollow(userId, followId);
    }

    public List<Follow> getFollowing(Long userId){
        return followRepository.getFollowings(userId);
    }

    public List<Follow> getFollower(Long userId){
        return followRepository.getFollowers(userId);
    }

    public Long getFollowingCount(Long userId){
        return followRepository.getFollowingCount(userId);
    }

    public Long getFollowerCount(Long userId){
        return followRepository.getFollowerCount(userId);
    }

}
