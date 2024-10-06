package inf.saveanimals.controller.login;

import inf.saveanimals.config.login.jwt.JwtProperties;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.users.LoginRequest;
import inf.saveanimals.request.users.UserCreate;
import inf.saveanimals.request.users.UserEdit;
import inf.saveanimals.response.users.LoginUserResponse;
import inf.saveanimals.response.users.UserInfo;
import inf.saveanimals.response.users.UserTokenDto;
import inf.saveanimals.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginApiController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    @GetMapping("/check-id")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String email) {
        userService.checkIdDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/register")
    public ResponseEntity<LoginUserResponse> register(@RequestBody UserCreate dto) {
        LoginUserResponse successMember = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody LoginRequest dto) {
        UserTokenDto loginDTO = userService.login(dto);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + loginDTO.getAccessToken())
                .header("refreshToken", "Bearer " + loginDTO.getRefreshToken())
                .body(loginDTO);
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<LoginUserResponse> check(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> request) {
        String password = request.get("password");
        LoginUserResponse memberInfo = userService.check(user, password);
        return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
    }


    @PutMapping("/update")
    public ResponseEntity<LoginUserResponse> update(
            @AuthenticationPrincipal User user,
            @RequestBody UserEdit dto) {
        LoginUserResponse memberUpdate = userService.update(user, dto);
        return ResponseEntity.status(HttpStatus.OK).body(memberUpdate);
    }


    @GetMapping("/user")
    public ResponseEntity<UserInfo> getUserInfo(@AuthenticationPrincipal User user) {
        UserInfo userInfo = userService.userInfoFindByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

/*
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {
        userService.logout(accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }
 */

    @DeleteMapping("/user/delete")
    public ResponseEntity<Void> withdrawUser(@RequestHeader("Authorization") String accessToken) {
        userService.withdrawUser(accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }

}
