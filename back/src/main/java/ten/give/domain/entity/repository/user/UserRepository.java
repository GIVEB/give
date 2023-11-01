package ten.give.domain.entity.repository.user;

import ten.give.domain.entity.user.User;
import ten.give.domain.exception.form.ResultForm;
import ten.give.web.form.UserInfoForm;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    //crud

    User saveUser(User user);

    void updateUser(Long userId, UserInfoForm updateParam);

    void deleteUserByUserId(Long userId);

    Optional<User> findUserByUserId(Long userId);

    List<User> findAll();

    Optional<User> findUserByEmail(String email);

    Long getDonationCount(Long userId);

    Long getTotalDonationCount();

    List<User> follow(Long loginId ,Long followingId);

    ResultForm deleteFollow(Long loginId, Long unfollowId);

    List<User> getFollowings(Long loginId);

    List<User> getFollower(Long loginId);


}
