package inf.saveanimals.domain.animals.common;

/**
 * 중성화 여부 데이터
 */
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
