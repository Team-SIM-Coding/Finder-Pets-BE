package inf.saveanimals.service;

import inf.saveanimals.config.login.auth.CustomUserDetailsService;
import inf.saveanimals.config.login.jwt.JwtProperties;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.ResourceNotFoundException;
import inf.saveanimals.exception.UserException;
import inf.saveanimals.exception.UserNotFound;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.users.LoginRequest;
import inf.saveanimals.request.users.UserCreate;
import inf.saveanimals.request.users.UserEdit;
import inf.saveanimals.response.users.LoginUserResponse;
import inf.saveanimals.response.users.UserTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtProperties jwtProperties;


    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);
    }


    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "User Email", email));
    }

    public HttpStatus checkIdDuplicate(String email) {
        isExistUserEmail(email);
        return HttpStatus.OK;
    }
    public LoginUserResponse signUp(UserCreate dto) {

        isExistUserEmail(dto.getEmail());
        checkPassword(dto.getPassword(), dto.getPasswordCheck());

        // 패스워드 암호화
        String encodePwd = encoder.encode(dto.getPassword());
        dto.setPassword(encodePwd);

        User user = userRepository.save(dto.ofEntity());

        return LoginUserResponse.fromEntity(user);
    }

    public UserTokenDto login(LoginRequest loginDto) {
        authenticate(loginDto.getEmail(), loginDto.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        checkEncodePassword(loginDto.getPassword(), userDetails.getPassword());
        String token = jwtProperties.generateToken(userDetails);
        return UserTokenDto.fromEntity(userDetails, token);
    }

    public LoginUserResponse check(User user, String password) {
        User checkUser = (User) userDetailsService.loadUserByUsername(user.getEmail());
        checkEncodePassword(password, checkUser.getPassword());
        return LoginUserResponse.fromEntity(checkUser);
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
     * 사용자 인증
     * @param email
     * @param pwd
     */
    private void authenticate(String email, String pwd) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
        } catch (DisabledException e) {
            throw new UserException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            throw new UserException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * 아이디(이메일) 중복 체크
     * @param email
     */
    private void isExistUserEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
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



    /**
     * 사용자가 입력한 비번과 DB에 저장된 비번이 같은지 체크 : 인코딩 확인
     * @param rawPassword
     * @param encodedPassword
     */
    private void checkEncodePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword)) {
            throw new UserException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }
}
