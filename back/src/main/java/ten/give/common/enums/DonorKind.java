package ten.give.common.enums;

public enum DonorKind {

    WHOLE(0),
    PLASMA(1),
    PLATELETS(2),
    PLATELETSPLASMA(3);

    private int kindCode;

    DonorKind(int kindCode) {
        this.kindCode = kindCode;
    }

    public int getKindCode() {
        return kindCode;
    }
}
