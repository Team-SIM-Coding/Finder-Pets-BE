package inf.saveanimals.domain.animals.common;

public enum NeuteringStatus {
    Y("예","Yes"),
    N("아니오","No"),
    U("미상","Unknown");

    private final String kCode;
    private final String neuterYn;

    NeuteringStatus(String kCode, String neuterYn) {
        this.kCode = kCode;
        this.neuterYn = neuterYn;
    }

}
