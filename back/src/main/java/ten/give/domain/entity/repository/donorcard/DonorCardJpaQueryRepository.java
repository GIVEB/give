package ten.give.domain.entity.repository.donorcard;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import ten.give.domain.entity.donorcard.DonorCard;

import javax.persistence.EntityManager;
import java.util.List;

import static ten.give.domain.entity.donorcard.QDonorCard.*;

@Repository
public class DonorCardJpaQueryRepository {

    private final JPAQueryFactory query;

    public DonorCardJpaQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<DonorCard> findAll(Long userId){
        return query.select(donorCard)
                .from(donorCard)
                .where(eqUserId(userId))
                .fetch();

    }

    private BooleanExpression eqUserId(Long userId){
        if (userId != null){
            return donorCard.user.userId.eq(userId);
        }
        return null;
    }

}
