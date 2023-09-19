package ten.give.domain.entity.repository.user;

import ten.give.domain.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    //crud

    User saveUser(User user);

    void updateUser(Long userId, User updateParam);

    void deleteUserByUserId(Long userId);

    Optional<User> findUserByUserId(Long userId);

    List<User> findAll();

    Optional<User> findUserByEmail(String email);

}