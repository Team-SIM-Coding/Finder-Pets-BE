package inf.saveanimals.domain.animals.common;

import lombok.Getter;

/**
 * 중성화 여부 데이터
 */
@Getter
public enum NeuteringStatus {
    Y("예","Yes", true),
    N("아니오","No", false),
    U("미상","Unknown", false);

    private final String kCode;
    private final String neuterYn;
    private final boolean is_neutering;

    NeuteringStatus(String kCode, String neuterYn, boolean is_neutering) {
        this.kCode = kCode;
        this.neuterYn = neuterYn;
        this.is_neutering = is_neutering;
    }

}
