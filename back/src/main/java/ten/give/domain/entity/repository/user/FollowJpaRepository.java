package ten.give.domain.entity.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ten.give.domain.entity.user.Follow;

@Component
public interface FollowJpaRepository extends JpaRepository<Follow,Long> {

//    @Query(value = "select f from Follow f where f.fromUser.userId = :fromId and f.toUser.userId = :toId")
//    void checkFollow(@Param("fromId") Long fromId,@Param("toID") Long toId);
}
