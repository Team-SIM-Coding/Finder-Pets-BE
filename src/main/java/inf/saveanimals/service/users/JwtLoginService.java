package inf.saveanimals.service.users;

import inf.saveanimals.auth.jwt.util.JwtProperties;
import inf.saveanimals.auth.security.CustomUserDetailsService;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.exception.users.UserNotFoundException;
import inf.saveanimals.repository.token.RefreshTokenRepository;
import inf.saveanimals.repository.users.UserRepository;
import inf.saveanimals.request.token.CreateRefreshToken;
import inf.saveanimals.request.users.LoginRequest;
import inf.saveanimals.response.users.UserTokenDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.Cookie;

import java.util.Collection;
import java.util.Iterator;

import static inf.saveanimals.utils.constants.JwtConstants.*;


@Service
@RequiredArgsConstructor
@Transactional
public class JwtLoginService {

    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    /**
     * 로그인
     */
    public UserTokenDto login(LoginRequest loginRequest, HttpServletResponse response) {
        // 인증된 회원정보인지 검증
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        // 패스워드 인코딩 검증
        checkEncodePassword(loginRequest.getPassword(), userDetails.getPassword());

        // get role
        String email = loginRequest.getEmail();
        String role = getRole(userDetails);

        String access = jwtProperties.createJwt(JWT_AUTH, email, role, ACCESS_TOKEN_EXPIRATION);
        String refresh = jwtProperties.createJwt(JWT_REFRESH, email, role, REFRESH_TOKEN_EXPIRATION);

        //Refresh 토큰 저장, cookie
        addRefreshEntity(new CreateRefreshToken(email, refresh, REFRESH_TOKEN_EXPIRATION));
        Cookie cookie = createCookie(JWT_REFRESH, refresh);

        //응답 설정
        response.setHeader(JWT_AUTH, access);
        response.addCookie(cookie);
        response.setStatus(HttpStatus.OK.value());

        return new UserTokenDto(JWT_AUTH, access, refresh);
    }

    private void addRefreshEntity(CreateRefreshToken refreshRequest) {

        refreshTokenRepository.save(refreshRequest.ofEntity());
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(COOKIE_TIME);
        cookie.setHttpOnly(true);

        return cookie;
    }
    private static String getRole(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();
        return role;
    }




    /**
     * 사용자가 입력한 비번과 DB에 저장된 비번이 같은지 체크 : 인코딩 확인
     * @param rawPassword
     * @param encodedPassword
     */
    private void checkEncodePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword)) {
            throw new UserNotFoundException("비밀번호 불일치");
        }
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
            throw new UserNotFoundException("가입되지 않은 아이디입니다.");
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException("비밀번호가 일치하지 않습니다.");
        }
    }


    /**
     * 회원탈퇴
     */
    public void withdrawUser(User user) {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        userRepository.delete(loginUser);
    }

}
