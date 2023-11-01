package ten.give.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ten.give.common.enums.Gender;
import ten.give.common.utils.GradeUtils;
import ten.give.web.form.UserInfoForm;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    private Gender gender;

    private String birth_year;

    private String birth_month;

    private String birth_day;

    private String phone;

    private String Address;

    private String Address_detail;

    private LocalDate joinDate;

    private Long donationCount;


    public UserInfoForm userTransferToUserInfo(Long totalDonationCount) {
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setName(this.name);
        userInfoForm.setBirth_year(this.birth_year);
        userInfoForm.setBirth_month(this.birth_month);
        userInfoForm.setBirth_day(this.birth_day);
        userInfoForm.setPhone(this.phone);
        userInfoForm.setAddress(this.getAddress());
        userInfoForm.setAddress_detail(this.getAddress_detail());
        userInfoForm.setDonationCount(this.donationCount);
        userInfoForm.setTotalDonationCount(totalDonationCount);
        userInfoForm.setGrade(GradeUtils.getGrade(this.donationCount,totalDonationCount));
        userInfoForm.setGender(this.gender.getGender());
        return userInfoForm;
    }

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public User() {

    }

}
