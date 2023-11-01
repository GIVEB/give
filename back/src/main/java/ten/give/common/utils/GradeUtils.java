package ten.give.common.utils;

import ten.give.common.enums.Grade;

public class GradeUtils {

    private static final float PAPERCUP = 0;
    private static final float MUGCUP = 10;
    private static final float WINEGLASS = 25;
    private static final float MILKCARTON = 45;
    private static final float PLASTICCUP = 65;
    private static final float WATERBOTTLE = 80;
    private static final float OILDRUM = 90;
    private static final float PURIFIEDTANK = 94;
    private static final float OILBARREL = 97;
    private static final float WATERTANK = 99;

    public static Grade getGrade(Long donationCount, Long totalDonationCount){

        double ratio = (donationCount / totalDonationCount) * 100;

        if (ratio < PAPERCUP){
            return Grade.PAPERCUP;
        }else if (ratio < MUGCUP){
            return Grade.MUGCUP;
        }else if (ratio < WINEGLASS){
            return Grade.WINEGLASS;
        }else if (ratio < MILKCARTON){
            return Grade.MILKCARTON;
        }else if (ratio < PLASTICCUP){
            return Grade.PLASTICCUP;
        }else if (ratio < WATERBOTTLE){
            return Grade.WATERBOTTLE;
        }else if (ratio < OILDRUM){
            return Grade.OILDRUM;
        }else if (ratio < PURIFIEDTANK){
            return Grade.PURIFIEDTANK;
        }else if (ratio < OILBARREL){
            return Grade.OILBARREL;
        }else {
            return Grade.WATERTANK;
        }

    }


}
