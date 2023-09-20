package ten.give.domain.entity.donorcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ten.give.common.enums.DonorCenter;
import ten.give.common.enums.DonorKind;
import ten.give.domain.entity.user.User;
import ten.give.common.enums.Gender;
import ten.give.web.form.DonorAddForm;
import ten.give.web.form.DonorCardInfoForm;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@Table(name = "donorCards")
@AllArgsConstructor
public class DonorCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @NotNull
    @Enumerated
    private DonorKind kind;

    @NotNull
    private LocalDate birth;

    @NotNull
    @NotEmpty
    @Column(length = 21)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate donorDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 200)
    private DonorCenter donorCenter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate registrationDate;

    public static DonorCard buildDonorCard(DonorAddForm form, User user){
        return new DonorCard().builder()
                .name(form.getName())
                .birth(form.getBirth())
                .user(user)
                .donorCenter(form.getDonorCenter())
                .donorDate(form.getDonorDate())
                .registrationDate(LocalDate.now())
                .kind(form.getKind())
                .gender(form.getGender())
                .build();
    }

    public DonorCard() {

    }

    public DonorCardInfoForm donorCardToDonorCardInfoForm() {
        DonorCardInfoForm donorInfo = new DonorCardInfoForm();
        donorInfo.setCardId(this.cardId);
        donorInfo.setName(this.name);
        donorInfo.setKind(this.kind);
        donorInfo.setGender(this.gender);
        donorInfo.setBirth(this.birth);
        donorInfo.setDonorDate(this.donorDate);
        donorInfo.setDonorCenter(this.donorCenter);
        donorInfo.setRegistrationDate(this.registrationDate);
        donorInfo.setUserId(this.user.getUserId());

        return donorInfo;
    }
}
