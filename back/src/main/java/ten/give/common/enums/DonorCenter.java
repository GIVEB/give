package ten.give.common.enums;

public enum DonorCenter {

    GANGWON(0),
    GYEONGNAM(1),
    GYEONGBUK(2),
    JEONBUK(3),
    CHUNGNAM(4);

    private int regionCode;

    DonorCenter(int regionCode) {
        this.regionCode = regionCode;
    }

    public int getRegionCode() {
        return regionCode;
    }
}

