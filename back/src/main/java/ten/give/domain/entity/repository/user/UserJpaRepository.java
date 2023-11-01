package ten.give.domain.entity.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ten.give.domain.entity.user.User;

import java.util.Optional;

@Component
public interface UserJpaRepository  extends JpaRepository<User,Long> {

    @Query(value = "select u from User u where u.account.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "select u.donationCount from User u where u.userId = :userId")
    Long getDonationCountByUserId(Long userId);

    @Query(value = "select SUM(u.donationCount) from User u")
    Long getTotalDonationCount();
}
