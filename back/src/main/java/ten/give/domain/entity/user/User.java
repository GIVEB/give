package ten.give.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@Entity
@Table(name = "users")
@SequenceGenerator(
        name = "USER_id_GENERATOR"
        , sequenceName = "USER_id"
        , initialValue = 2
        , allocationSize = 1
)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
            , generator = "USER_id_GENERATOR"
    )
    private Long userId;

    @NotNull
    @NotEmpty
    @Column(length = 21)
    private String name;

    @Email
    @NotNull
    @NotEmpty
    @Column(length = 150)
    private String email;

    @NotNull
    @NotEmpty
    @Column(length = 150)
    private String password;

    public User() {

    }
}
