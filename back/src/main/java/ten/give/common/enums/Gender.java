package ten.give.common.enums;

public enum Gender {

    M("MALE"),
    F("FEMALE");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
