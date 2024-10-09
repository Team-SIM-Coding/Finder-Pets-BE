package inf.saveanimals.service.users;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.*;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.users.UserCreate;
import inf.saveanimals.request.users.UserEdit;
import inf.saveanimals.request.users.UserInfoUpdate;
import inf.saveanimals.response.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 회원 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;



    @Transactional(readOnly = true)
    public UserInfo userInfoFindByUser(User user) {
        User loginUser = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail()));

        return UserInfo.fromEntity(loginUser);
    }


    public UserInfo updateUserInfo(User user, UserInfoUpdate updateDto) {

        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        loginUser.updateUserInfo(updateDto);

        return UserInfo.fromEntity(loginUser);
    }


    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", email));
    }


    public SignupResponse signUp(UserCreate request) {

        if (checkEmailDuplicate(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        if (checkNameDuplicate(request.getName())) {
            throw new DuplicateNameException();
        }

        // 패스워드 암호화
        request.encodePwd(encoder);

        // 저장
        User user = userRepository.save(request.ofEntity());

        return SignupResponse.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email); // 중복이면 true로 반환해달라는 fe 요청
    }

    @Transactional(readOnly = true)
    public boolean checkNameDuplicate(String name) {
        return userRepository.existsByName(name);
    }





    public LoginUserResponse update(User user, UserEdit dto) {
        checkPassword(dto.getPassword(), dto.getPasswordCheck());
        String encodePwd = encoder.encode(dto.getPassword());
        User updateUser =  userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", user.getEmail())
        );
        updateUser.update(encodePwd, dto.getUsername());
        return LoginUserResponse.fromEntity(updateUser);
    }





    /**
     * 비밀번호와 비밀번호 확인이 같은지 체크
     * @param password
     * @param passwordCheck
     */
    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new UserException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }



}
