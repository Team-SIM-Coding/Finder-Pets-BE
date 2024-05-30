package inf.saveanimals.controller;

import inf.saveanimals.domain.animals.common.Breed;
import inf.saveanimals.domain.animals.common.Gender;
import inf.saveanimals.domain.animals.common.NeuteringStatus;

import inf.saveanimals.domain.posts.lost.LostComments;
import inf.saveanimals.domain.posts.lost.LostPets;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.repository.posts.lost.LostCommentsRepository;
import inf.saveanimals.repository.posts.lost.LostPetsRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.posts.lost.LostCommentCreate;
import inf.saveanimals.request.posts.lost.LostPetsCreate;
import inf.saveanimals.service.posts.lostPets.LostPetsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LostCommentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private LostPetsRepository petsRepository;
    
    @Autowired
    private LostPetsService lostPetsService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LostCommentsRepository commentsRepository;
    
    @AfterEach
    void clean() {
        commentsRepository.deleteAll();
        petsRepository.deleteAll();
        userRepository.deleteAll();
    }
    
    @Test
    @DisplayName("댓글 작성하기")
    void addComments_success() throws Exception {
        //given
        User user = User.builder()
                .name("none")
                .email("none1234@gmail.com")
                .nickname("noneNick")
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
        LostPets lostPets = lostPetsService.findById(postId);

        LostCommentCreate commentCreate = LostCommentCreate.builder()
                .user_nickname("noneNick")
                .user_image("/imgpath")
                .content("댓글입니다. 안녕하세요.")
                .build();

        String json = objectMapper.writeValueAsString(commentCreate);


        mockMvc.perform(post("/lostPets/{postId}/comments", lostPets.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(1L, commentsRepository.count());

        LostComments comments = commentsRepository.findAll().get(0);
        assertEquals("noneNick", comments.getUser_nickname());
        assertEquals("댓글입니다. 안녕하세요.", comments.getContent());
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
