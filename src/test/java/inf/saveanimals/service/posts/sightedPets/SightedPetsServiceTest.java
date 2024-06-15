package inf.saveanimals.service.posts.sightedPets;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.posts.sighted.SightedPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.repository.posts.lost.LostImgRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.posts.sighted.SightedImgRepository;
import inf.saveanimals.repository.posts.sighted.SightedPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.lost.LostPetsCreate;
import inf.saveanimals.request.posts.lost.LostPetsEdit;
import inf.saveanimals.request.posts.sighted.SightedPetsCreate;
import inf.saveanimals.request.posts.sighted.SightedPetsEdit;
import inf.saveanimals.service.posts.lostPets.LostPetsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@Transactional
class SightedPetsServiceTest {

    @Autowired
    private SightedPetsService sightedPetsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SightedPetsRepository sightedPetsRepository;

    @Autowired
    private SightedImgRepository imgRepository;

    @BeforeEach
    void clean() {
        sightedPetsRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("글 등록")
    void write_post_success() throws IOException {
        // given
        User user = User.builder()
                .name("none")
                .email("none1234@gmail.com")
                .password("1234")
                .img("/imgpath")
                .build();
        userRepository.save(user);

        SightedPetsCreate postCreate = SightedPetsCreate.builder()
                .createdAt(LocalDateTime.of(2023, 6, 7, 15, 30, 45))
                .foundDate(LocalDateTime.of(2023, 6, 7, 15, 30, 45))
                .foundPlace("서울특별시 광진구 군자역인근")
                .specialMark("노견이라 매우 말랐고 인지능력이 떨어져요. 피부병도 조금")
                .reporterTel("123-123")
                .latitude("45")
                .longitude("45")
                .city(City.GWANGJIN_GU)
                .districts(Districts.SEOUL_CITY)
                .breed(Breed.SCHNAUZER)
                .build();



        List<MultipartFile> multipartFileList = generateMultipartFileList();

        // when
        Long postId = sightedPetsService.write(user, postCreate, multipartFileList);


        // then
        SightedPets post = sightedPetsRepository.findAll().get(0);
        assertEquals(Breed.SCHNAUZER, post.getBreed());
        assertEquals("노견이라 매우 말랐고 인지능력이 떨어져요. 피부병도 조금", post.getSpecialMark());
    }

    @Test
    @DisplayName("작성된 글을 수정")
    void edit_post_success() throws IOException {
        // given
        User user = User.builder()
                .name("none")
                .email("none1234@gmail.com")
                .password("1234")
                .img("/imgpath")
                .build();
        userRepository.save(user);

        SightedPetsCreate postCreate = SightedPetsCreate.builder()
                .createdAt(LocalDateTime.of(2023, 6, 7, 15, 30, 45))
                .foundDate(LocalDateTime.of(2023, 6, 7, 15, 30, 45))
                .foundPlace("서울특별시 광진구 군자역인근")
                .specialMark("노견이라 매우 말랐고 인지능력이 떨어져요. 피부병도 조금")
                .reporterTel("123-123")
                .latitude("45")
                .longitude("45")
                .city(City.GWANGJIN_GU)
                .districts(Districts.SEOUL_CITY)
                .breed(Breed.SCHNAUZER)
                .build();

        List<MultipartFile> multipartFileList = generateMultipartFileList();

        Long postId = sightedPetsService.write(user, postCreate, multipartFileList);


        SightedPetsEdit postEdit = SightedPetsEdit.builder()
                .foundDate(LocalDateTime.of(2023, 6, 6, 15, 30, 45))
                .build();

        // when
        sightedPetsService.edit(postId, postEdit);

        SightedPets foundPost = sightedPetsService.findById(postId);

        // then
        assertEquals(LocalDateTime.of(2023, 6, 6, 15, 30, 45), foundPost.getFoundDate());

    }

    @Test
    @DisplayName("글 삭제")
    void delete_success() throws IOException {
        // given
        User user = User.builder()
                .name("none")
                .email("none1234@gmail.com")
                .password("1234")
                .img("/imgpath")
                .build();
        userRepository.save(user);

        SightedPetsCreate postCreate = SightedPetsCreate.builder()
                .createdAt(LocalDateTime.of(2023, 6, 7, 15, 30, 45))
                .foundDate(LocalDateTime.of(2023, 6, 7, 15, 30, 45))
                .foundPlace("서울특별시 광진구 군자역인근")
                .specialMark("노견이라 매우 말랐고 인지능력이 떨어져요. 피부병도 조금")
                .reporterTel("123-123")
                .latitude("45")
                .longitude("45")
                .city(City.GWANGJIN_GU)
                .districts(Districts.SEOUL_CITY)
                .breed(Breed.SCHNAUZER)
                .build();

        List<MultipartFile> multipartFileList = generateMultipartFileList();

        Long postId = sightedPetsService.write(user, postCreate, multipartFileList);

        //when
        sightedPetsService.deletePost(postId);

        //then
        assertEquals(0, sightedPetsRepository.count());
    }

    private static List<MultipartFile> generateMultipartFileList() {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<2; i++){ // 상품 이미지 경로 + 이미지 이름 저장해서 add
            String path = "C:/shop/item/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }
}