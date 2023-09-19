package ten.give.domain.entity.repository.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ten.give.domain.entity.user.User;

import javax.persistence.EntityManager;
import java.util.List;

import static ten.give.domain.entity.user.QUser.*;

@Repository
public class UserJpaQueryRepository {

    private final JPAQueryFactory query;

    public UserJpaQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<User> findUserByEmail(String email) {
            return query.select(user)
                    .from(user)
                    .where(eqEmail(email))
                    .fetch();
    }

    private BooleanExpression eqEmail(String email){
        if (StringUtils.hasText(email)){
            return user.email.eq(email);
        }
        return null;
    }

}
