package ten.give.common.enums;

public enum Grade {

    PAPERCUP("papercup"),
    MUGCUP("mugcup"),
    WINEGLASS("wineglass"),
    MILKCARTON("milkcarton"),
    PLASTICCUP("plasticcup"),
    WATERBOTTLE("waterbottle"),
    OILDRUM("oildrum"),
    PURIFIEDTANK("purifiedtank"),
    OILBARREL("oilbarrel"),
    WATERTANK("wartertank");

    private String grade;

    Grade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

}
