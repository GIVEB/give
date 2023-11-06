package ten.give.domain.entity.repository.user;

import ten.give.domain.entity.user.User;
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

    User findUserByNameAndPhoneNumber(String name, String phoneNumber);
}
