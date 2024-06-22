package inf.saveanimals.controller.lost;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import inf.saveanimals.service.posts.lostPets.LostPetsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LostPostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ResourceLoader loader;

    @Autowired
    private LostPetsService lostPetsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LostPetsRepository lostPetsRepository;

    @Autowired
    private LostImgRepository imgRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @BeforeEach
    void clean() {
        lostPetsRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterEach
    void cleanAfter() {
        lostPetsRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void create_post_success() throws Exception {
        //given
        User user = getUser();
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        LostPetsCreate postCreate = getLostPetsCreate();
        MockMultipartFile request = new MockMultipartFile("postCreate", null, "application/json", objectMapper.writeValueAsString(postCreate).getBytes(StandardCharsets.UTF_8));
;
        List<MockMultipartFile> multipartFiles = getMockMultipartFiles();

        // when
        mockMvc.perform(MockMvcRequestBuilders
                .multipart(HttpMethod.POST, "/api/posts/lost")
                        .file(request)
                        .file(multipartFiles.get(0))
                        .file(multipartFiles.get(1))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(SecurityMockMvcRequestPostProcessors.user(userDetails))
               )
                .andExpect(status().isOk())
                .andDo(print());


        // then
        LostPets post = lostPetsRepository.findAll().get(0);
        assertEquals(Breed.AKITA, post.getBreed());
        assertEquals("파란색목줄착용, 겁이많음,진드기", post.getSpecialMark());

        log.info("saved post={}", post.getId(), post.getAge());
        log.info("saved post={}", post.getAge());
    }

    private User getUser() {
        User user = User.builder()
                //.name("none")
                .email("none1234@gmail.com")
                .password("1234")
              //  .img("/imgpath")
                .build();
        userRepository.save(user);
        return user;
    }

    private static LostPetsCreate getLostPetsCreate() {
        LostPetsCreate postCreate = LostPetsCreate.builder()
                .kind(Breed.AKITA)
                .gender(Gender.FEMALE)
                .weight("10살")
                .color("black")
                .age("19(년생)")
                .is_neutering(NeuteringStatus.Y)
                .character("파란색목줄착용, 겁이많음,진드기")
                .phone("123-123")
                .area("공주시 우금티터널")
                .city(City.GONGJU_SI)
                .districts(Districts.CHUNGCHEONGNAM_CITY)
                .detailed("상세하게")
                .build();

        return postCreate;
    }

    private List<MockMultipartFile> getMockMultipartFiles() throws IOException {
        List<MockMultipartFile> multipartFiles = new ArrayList<>();
        Resource res1 = loader.getResource("classpath:/static/img/banana.jpg");
        Resource res2 = loader.getResource("classpath:/static/img/tomato.jpg");
        MockMultipartFile file1 = new MockMultipartFile("files", "banana.jpg", "multipart/form-data", res1.getInputStream());
        MockMultipartFile file2 = new MockMultipartFile("files", "tomato.jpg", "multipart/form-data", res2.getInputStream());
        multipartFiles.add(file1);
        multipartFiles.add(file2);
        return multipartFiles;
    }


}