package ten.give.domain.entity.repository.user;

import ten.give.domain.entity.user.Follow;
import ten.give.domain.exception.form.ResultForm;

import java.util.List;

public interface FollowRepository {

    boolean checkFollow(Long fromId, Long toId);
    ResultForm follow(Long fromId, Long toId);
    ResultForm unFollow(Long fromId, Long toId);
    List<Follow> getFollowings(Long loginId);
    List<Follow> getFollowers(Long loginId);
    Long getFollowingCount(Long loginId);
    Long getFollowerCount(Long loginId);

}
