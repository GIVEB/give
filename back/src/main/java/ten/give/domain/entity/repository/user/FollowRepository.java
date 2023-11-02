package ten.give.domain.entity.repository.user;

import ten.give.domain.entity.user.User;
import ten.give.domain.exception.form.ResultForm;

import java.util.List;

public interface FollowRepository {

    boolean checkFollow(Long fromId, Long toId);
    ResultForm Follow(Long fromId, Long toId);
    ResultForm unFollow(Long fromId, Long toId);
    List<User> getFollowings(Long loginId);
    List<User> getFollowers(Long loginId);
    Long getFollowingCount(Long loginId);
    Long getFollowwerCount(Long loginId);

}
