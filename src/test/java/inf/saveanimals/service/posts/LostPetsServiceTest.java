package inf.saveanimals.service.posts;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;
import inf.saveanimals.domain.areas.City;
import inf.saveanimals.domain.areas.Districts;
import inf.saveanimals.domain.posts.lost.LostPets;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.repository.posts.lost.LostImgRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.lost.LostPetsCreate;

import inf.saveanimals.request.posts.lost.LostPetsEdit;
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
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class LostPetsServiceTest {

    @Autowired
    private LostPetsService lostPetsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LostPetsRepository lostPetsRepository;

    @Autowired
    private LostImgRepository imgRepository;

    @BeforeEach
    void clean() {
        lostPetsRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("글 작성")
    void create_post_success() throws IOException {
        //given
        User user = User.builder()
                .name("none")
                .email("none1234@gmail.com")
                .password("1234")
                .img("/imgpath")
                .build();
        userRepository.save(user);

        LostPetsCreate postCreate = LostPetsCreate.builder()
                .breed(Breed.AKITA)
                .gender(Gender.FEMALE)
                .weight(10)
                .color("black")
                .age("19(년생)")
                .neuteringStatus(NeuteringStatus.Y)
                .specialMark("파란색목줄착용, 겁이많음,진드기")
                .petOwnerTel("123-123")
                .content("중요")
                .happenPlace("공주시 우금티터널")
                .city(City.GONGJU_SI)
                .districts(Districts.CHUNGCHEONGNAM_CITY)
                .build();


        List<MultipartFile> multipartFileList = generateMultipartFileList();

        // when
        lostPetsService.write(user.getId(), postCreate, multipartFileList);

        //then
        LostPets post = lostPetsRepository.findAll().get(0);
        assertEquals(Breed.AKITA, post.getBreed());
        assertEquals("파란색목줄착용, 겁이많음,진드기", post.getSpecialMark());

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

        LostPetsCreate postCreate = LostPetsCreate.builder()
                .breed(Breed.AKITA)
                .gender(Gender.FEMALE)
                .weight(10)
                .color("black")
                .age("19(년생)")
                .neuteringStatus(NeuteringStatus.Y)
                .specialMark("파란색목줄착용, 겁이많음,진드기")
                .petOwnerTel("123-123")
                .content("중요")
                .happenPlace("공주시 우금티터널")
                .city(City.GONGJU_SI)
                .districts(Districts.CHUNGCHEONGNAM_CITY)
                .build();


        List<MultipartFile> multipartFileList = generateMultipartFileList();
        Long postId = lostPetsService.write(user.getId(), postCreate, multipartFileList);


        LostPetsEdit postEdit = LostPetsEdit.builder()
                .content("매우 중요하다.")
                .build();

        // when
        lostPetsService.edit(postId, postEdit);

        LostPets foundPost = lostPetsService.findById(postId);
        log.info("post info={}", foundPost.getSpecialMark());

        // then
        assertEquals("매우 중요하다.", foundPost.getContent());

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

        LostPetsCreate postCreate = LostPetsCreate.builder()
                .breed(Breed.AKITA)
                .gender(Gender.FEMALE)
                .weight(10)
                .color("black")
                .age("19(년생)")
                .neuteringStatus(NeuteringStatus.Y)
                .specialMark("파란색목줄착용, 겁이많음,진드기")
                .petOwnerTel("123-123")
                .content("중요")
                .happenPlace("공주시 우금티터널")
                .build();


        List<MultipartFile> multipartFileList = generateMultipartFileList();
        Long postId = lostPetsService.write(user.getId(), postCreate, multipartFileList);

        //when
        lostPetsService.deletePost(postId);

        //then
        assertEquals(0, lostPetsRepository.count());

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