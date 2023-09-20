package ten.give.domain.entity.donorcard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDonorCard is a Querydsl query type for DonorCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDonorCard extends EntityPathBase<DonorCard> {

    private static final long serialVersionUID = -1354063567L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDonorCard donorCard = new QDonorCard("donorCard");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final NumberPath<Long> cardId = createNumber("cardId", Long.class);

    public final EnumPath<ten.give.common.enums.DonorCenter> donorCenter = createEnum("donorCenter", ten.give.common.enums.DonorCenter.class);

    public final DatePath<java.time.LocalDate> donorDate = createDate("donorDate", java.time.LocalDate.class);

    public final EnumPath<ten.give.common.enums.Gender> gender = createEnum("gender", ten.give.common.enums.Gender.class);

    public final EnumPath<ten.give.common.enums.DonorKind> kind = createEnum("kind", ten.give.common.enums.DonorKind.class);

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> registrationDate = createDate("registrationDate", java.time.LocalDate.class);

    public final ten.give.domain.entity.user.QUser user;

    public QDonorCard(String variable) {
        this(DonorCard.class, forVariable(variable), INITS);
    }

    public QDonorCard(Path<? extends DonorCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDonorCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDonorCard(PathMetadata metadata, PathInits inits) {
        this(DonorCard.class, metadata, inits);
    }

    public QDonorCard(Class<? extends DonorCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new ten.give.domain.entity.user.QUser(forProperty("user")) : null;
    }

}

