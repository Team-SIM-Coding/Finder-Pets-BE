package inf.saveanimals.domain.posts;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ShelterPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String happenDt; //접수일
    private String happenPlace; // 발견장소
    private String kindCd; // 품종
    private String colorCd; // 색상
    private String age; // 나이, 예시 ->2019(년생)
    private String weight; // 체중, 예시 -> 5(kg)
    private String profile; // 사진
    private String processStatus; // 상태, 예시 ->보호중
    private String sexCd; // 성별, 예시 ->M(수컷)
    private String neuterYn; // 중성화여부, ->N(아니오)
    private String specialMark; //특징
    private String careNm; //보호소 이름
    private String careTel; // 보호소전화번호
    private String careAddr; // 보호 장소(주소)
    private String chargeNm; //담당자
    private String officetel; // 담당자 연락처



}
