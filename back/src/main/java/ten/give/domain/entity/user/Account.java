package ten.give.domain.entity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Email
    @NotNull
    @NotEmpty
    @Column(length = 150, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(length = 150)
    private String password;

    public Account() {

    }

}
