package ten.give.domain.entity.repository.donorcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ten.give.domain.entity.donorcard.DonorCard;

@Component
public interface DonorCardJpaRepository extends JpaRepository<DonorCard,Long> {

    //메소드로 jpql 자동 생성
    Integer countByUser_UserId(Long userId);
}
