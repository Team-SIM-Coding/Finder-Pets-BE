package inf.saveanimals.domain.animals.common;

public enum Gender {

    MALE("수컷", "M"),
    FEMALE("암컷","F"),
    UNKNOWN("모름", "Q");

    private final String kCode;
    private final String sexCode;

    Gender(String kCode, String sexCode) {
        this.kCode = kCode;
        this.sexCode = sexCode;
    }
}
