package ten.give.domain.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="fromUser")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "toUser")
    private User toUser;

    @Transient
    private boolean matpal;

    @CreationTimestamp
    private LocalDate createDate;

    @CreationTimestamp
    private LocalDate updatDate;

}
