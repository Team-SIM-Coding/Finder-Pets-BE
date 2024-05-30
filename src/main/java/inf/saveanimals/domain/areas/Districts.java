package inf.saveanimals.domain.areas;

import static inf.saveanimals.domain.areas.City.*;
import lombok.Getter;

import java.util.Arrays;
@Getter
public enum Districts {
    SEOUL_CITY("서울특별시", new City[]{
            GANGNAM_GU,
            GANGDONG_GU,
            GANGBUK_GU,
            SEOUL_GANGSEO_GU,
            GWANAK_GU,
            GWANGJIN_GU,
            GURO_GU,
            GEUMCHEON_GU,
            NOWON_GU,
            DOBONG_GU,
            DONGDAEMUN_GU,
            DONGJAK_GU,
            MAPO_GU,
            SEODAEMUN_GU,
            SEOCHO_GU,
            SEONGDONG_GU,
            SEONGBUK_GU,
            SONGPA_GU,
            YANGCHEON_GU,
            YEONGDEUNGPO_GU,
            YONGSAN_GU,
            EUNPYEONG_GU,
            JONGNO_GU,
            SEOUL_JUNG_GU,
            JUNGNANG_GU
    }),
    BUSAN_CITY("부산광역시", new City[]{
            BUSAN_GANGSEO_GU,
            GEUMJEONG_GU,
            GIJANG_GUN,
            BUSAN_NAM_GU,
            BUSAN_DONG_GU,
            DONGNAE_GU,
            BUSANJIN_GU,
            BUSAN_BUK_GU,
            SASANG_GU,
            SAHA_GU,
            BUSAN_SEO_GU,
            SUYEONG_GU,
            YEONJE_GU,
            YEONGDO_GU,
            BUSAN_JUNG_GU,
            HAEUNDAE_GU
    }),
    DAEGU_CITY("대구광역시", new City[]{
            GUNWI_GUN,
            DAEGU_NAM_GU,
            DALSEO_GU,
            DALSEONG_GUN,
            DAEGU_DAEGU_DONG_GU,
            DAEGU_DAEGU_BUK_GU,
            DAEGU_DAEGU_SEO_GU,
            SUSEONG_GU,
            DAEGU_JUNG_GU,
    }),
    INCHEON_CITY("인천광역시", new City[]{
            GANGHWA_GUN,
            GYERYANG_GU,
            NAMDONG_GU,
            INCHEON_DONG_GU,
            MICHUHOL_GU,
            BUPYEONG_GU,
            INCHEON_SEO_GU,
            YEONSU_GU,
            ONGJIN_GUN,
            INCHEON_JUNG_GU,
    }),
    GWANGJU_CITY("광주광역시", new City[]{
            GWANGSAN_GU,
            GWANGJU_NAM_GU,
            GWANGJU_DONG_GU,
            GWANGJU_BUK_GU,
            GWANGJU_SEO_GU
    }),
    SEJONG_CITY("세종특별자치시", new City[]{
            SEJONG,
    }),
    DAEJEON_CITY("대전광역시", new City[]{
            DAEDEOK_GU,
            DAEJEON_DONG_GU,
            DAEJEON_SEO_GU,
            YUSEONG_GU,
            DAEJEON_JUNG_GU
    }),
    ULSAN_CITY("울산광역시", new City[]{
            ULSAN_NAM_GU,
            ULSAN_DONG_GU,
            ULSAN_BUK_GU,
            ULJU_GUN,
            ULSAN_JUNG_GU
    }),
    GYEONGGI_CITY("경기도", new City[]{
            GAPYEONG_GUN,
            GOYANG_SI,
            GWAECHEON_SI,
            GWANGMYEONG_SI,
            GWANGJU_SI,
            GURI_SI,
            GUNPO_SI,
            GIMPO_SI,
            NAMYANGJU_SI,
            DONGDUCHEON_SI,
            BUCHEON_SI,
            SEONGNAM_SI,
            SUWON_SI,
            SIHEUNG_SI,
            ANSAN_SI,
            ANSEONG_SI,
            ANYANG_SI,
            YANGJU_SI,
            YANGPYEONG_GUN,
            YEOJU_SI,
            YEONCHEON_GUN,
            OSAN_SI,
            YONGIN_SI,
            YONGIN_SI_GIHEUNGGU,
            UIWANG_SI,
            UIJEONGBU_SI,
            ICHEON_SI,
            PAJU_SI,
            PYEONGTAEK_SI,
            POCHON_SI,
            HANAM_SI,
            HWASEONG_SI
    }),
    GANGWON_CITY("강원도", new City[]{
            GANGNEUNG_SI,
            GANGWON_GOSEONG_GUN,
            DONGHAE_SI,
            SAMCHEOK_SI,
            SOKCHO_SI,
            YANGGU_GUN,
            YANGYANG_GUN,
            YEONGWOL_GUN,
            WONJU_SI,
            INJE_GUN,
            JEONGSEON_GUN,
            CHEORWON_GUN,
            CHUNCHEON_SI,
            TAEBAEK_SI,
            PYEONGCHANG_GUN,
            HONGCHEON_GUN,
            HWACHEON_GUN,
            HOENGSEONG_GUN
    }),
    CHUNGCHEONGBUK_CITY("충청북도", new City[]{
            GOESAN_GUN,
            DANYANG_GUN,
            BOEUN_GUN,
            YEONGDONG_GUN,
            OKCHEON_GUN,
            EUMSEONG_GUN,
            JECHEON_SI,
            JEUNGPYEONG_GUN,
            JINCHEON_GUN,
            CHEONGJU_SI,
            CHUNGJU_SI
    }),
    CHUNGCHEONGNAM_CITY("충청남도", new City[]{
            GYERYONG_SI,
            GONGJU_SI,
            GEUMSAN_GUN,
            NONSAN_SI,
            DANGJIN_SI,
            BORYEONG_SI,
            BUYEO_GUN,
            SEOSAN_SI,
            SEOCHEON_GUN,
            ASAN_SI,
            YEONKI_GUN,
            YESAN_GUN,
            CHEONAN_SI,
            CHEONGYANG_GUN,
            TAEAN_GUN,
            HONGSEONG_GUN
    }),
    JEOLLABUT_CITY("전라북도", new City[]{
            GOCHANG_GUN,
            GUNSAN_SI,
            GIMJE_SI,
            NAMWON_SI,
            MUJU_GUN,
            BUAN_GUN,
            SUNCHANG_GUN,
            WANJU_GUN,
            IKSAN_SI,
            IMSIL_GUN,
            JANGSU_GUN,
            JEONJU_SI,
            JEONGEUP_SI,
            JINAN_GUN
    }),
    JEOLLANAM_CITY("전라남도", new City[]{
            GANGJIN_GUN,
            GOHEUNG_GUN,
            GOKSEONG_GUN,
            GWANGYANG_SI,
            GURYE_GUN,
            NAJU_SI,
            DAMYANG_GUN,
            MOKPO_SI,
            MUAN_GUN,
            BOSEONG_GUN,
            SUNGCHEON_SI,
            SINAN_GUN,
            YEOSU_SI,
            YEONGGWANG_GUN,
            WANDO_GUN,
            JANGSEONG_GUN,
            JANGHEUNG_GUN,
            JINDO_GUN,
            HAMPYEONG_GUN,
            HAENAM_GUN,
            HWASUN_GUN
    }),
    GYEONGSANGBUK_CITY("경상북도", new City[]{
            GYEONGSAN_SI,
            GYEONGJU_SI,
            GORYEONG_GUN,
            GUMI_SI,
            GIMCHEON_SI,
            MOONKYUNG_SI,
            BONGHWA_GUN,
            SANGJU_SI,
            SEONGJU_GUN,
            ANDONG_SI,
            YEONGDEOK_GUN,
            YEONGYANG_GUN,
            YEONGJU_SI,
            YEONGCHUN_SI,
            YECHON_GUN,
            ULLEUNG_GUN,
            ULJIN_GUN,
            UISEONG_GUN,
            CHEONGDO_GUN,
            CHEONGSONG_GUN,
            CHILGOK_GUN,
            POHANG_SI
    }),
    GYEONGSANGNAM_CITY("경삼남도", new City[]{
            GEOJE_SI,
            GEOCHANG_GUN,
            GYEONGSANGNAM_GOSEONG_GUN,
            GIMHAE_SI,
            NAMHAE_GUN,
            MIRYANG_SI,
            SACHEON_SI,
            SANSCHEONG_GUN,
            YANGSAN_SI,
            UIRYEONG_GUN,
            JINJU_SI,
            CHANGNYEONG_GUN,
            CHANGWON_MASANHAPPOHOEWEON_GU,
            CHANGWON_SINGISAN_GU,
            CHANGWON_JINHAE_GU,
            TONGYEONG_SI,
            HADONG_GUN,
            HAMAN_GUN,
            HAMYANG_GUN,
            HAPCHEON_GUN
    }),
    JEJU_CITY("제주특별자치도", new City[]{
            JEJU_SI,
            SEOGWIPO_SI
    }),
    ETC("기타", new City[]{});



    private String cityName;
    private City[] containCities;

    Districts(String cityName, City[] containCities) {
        this.cityName = cityName;
        this.containCities = containCities;
    }



    public static Districts findDistrict(City searchCity) {
        return Arrays.stream(Districts.values())
                .filter(group -> hasCityOption(group, searchCity))
                .findAny()
                .orElse(Districts.ETC);

    }

    private static boolean hasCityOption(Districts from, City searchCity) {
        return Arrays.stream(from.containCities)
                .anyMatch(containCity -> containCity == searchCity);
    }
}
