package ten.give.domain.entity.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ten.give.domain.entity.user.Follow;

import java.util.List;
import java.util.Optional;

@Component
public interface FollowJpaRepository extends JpaRepository<Follow,Long> {

    @Query(value = "select f from Follow f where f.fromUser.userId = :fromId and f.toUser.userId = :toId")
    Optional<Follow> checkFollow(@Param("fromId") long fromId, @Param("toId") long toId);


    @Modifying
    @Query(value = "delete from Follow f where f.fromUser.userId = :fromId and f.toUser.userId = :toId")
    void deleteFollow(@Param("fromId") long fromId, @Param("toId") long toId);

    @Query(value = "select f from Follow f where f.fromUser.userId = :fromId")
    List<Follow> findAllByFromId(@Param("fromId") Long fromId);

    @Query(value = "select f from Follow f where f.toUser.userId = :fromId")
    List<Follow> findAllByToId(@Param("fromId") Long fromId);

    @Query(value = "select count(f) from Follow f where f.fromUser.userId = :fromId")
    Long countFollowing(@Param("fromId") Long fromId);

    @Query(value = "select count(f) from Follow f where f.toUser.userId = :fromId")
    Long countFollower(@Param("fromId")Long fromId);
}
