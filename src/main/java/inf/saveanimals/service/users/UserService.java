package inf.saveanimals.service.users;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.users.DuplicateEmailException;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.users.UserCreate;
import inf.saveanimals.request.users.UserInfoUpdate;
import inf.saveanimals.response.users.*;
import inf.saveanimals.service.posts.FileService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 회원 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final FileService fileService;

    /**
     *  유저정보 조회
     */
    @Transactional(readOnly = true)
    public UserInfo userInfoFindByUser(User user) {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 회원입니다."));

        return UserInfo.fromEntity(loginUser);
    }

    /**
     *  유저 정보 업데이트
     */
    public UserInfo updateUserInfo(User user, UserInfoUpdate updateDto) {

        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        loginUser.updateUserInfo(updateDto);

        return UserInfo.fromEntity(loginUser);
    }

    /**
     * 유저 프로필 업데이트
     */
    public void updateProfile(User user, MultipartFile multipartFile) throws IOException {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        String profile = uploadProfile(multipartFile);

        loginUser.updateProfileImg(profile);
    }

    // 유저 프로필 사진 업로드
    private String uploadProfile(MultipartFile file) throws IOException {
        return fileService.storeFile(file);
    }


    /**
     *  회원가입
     */
    public SignupResponse signUp(UserCreate request) {

        // 중복된 이메일 체크
        if (checkEmailDuplicate(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        // 패스워드 암호화
        request.encodePwd(encoder);

        // 저장
        User user = userRepository.save(request.ofEntity());

        return SignupResponse.fromEntity(user);
    }


    /**
     *  이메일 아이디 중복체크
     */
    @Transactional(readOnly = true)
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email); // 중복이면 true로 반환해달라는 fe 요청
    }


    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 회원입니다."));
    }








}
