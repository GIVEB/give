package ten.give.domain.entity.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FollowJpaQueryRepository {

    private final JPAQueryFactory query;

    public FollowJpaQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }



}
