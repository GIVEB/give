package ten.give.domain.entity.repository.account;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AccountJpaQueryRepository {

    private QueryFactory query;

    public AccountJpaQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }



}
