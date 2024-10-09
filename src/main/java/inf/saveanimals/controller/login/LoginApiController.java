package inf.saveanimals.controller.login;

import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.users.LoginRequest;
import inf.saveanimals.request.users.UserCreate;
import inf.saveanimals.request.users.UserEdit;
import inf.saveanimals.request.users.UserInfoUpdate;
import inf.saveanimals.response.users.LoginUserResponse;
import inf.saveanimals.response.users.SignupResponse;
import inf.saveanimals.response.users.UserInfo;
import inf.saveanimals.response.users.UserTokenDto;
import inf.saveanimals.service.users.JwtLoginService;
import inf.saveanimals.service.users.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginApiController {

    private final UserService userService;
    private final JwtLoginService jwtLoginService;

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkIdDuplicate(@RequestParam(name="email") String email) {
        boolean isDuplicated = userService.checkEmailDuplicate(email);
        return ResponseEntity.status(HttpStatus.OK).body(isDuplicated);
    }

    @PostMapping("/register")
    public ResponseEntity<SignupResponse> register(@RequestBody UserCreate dto) {
        SignupResponse successMember = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        UserTokenDto loginDTO = jwtLoginService.login(loginRequest, response);
        return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<UserInfo> getUserInfo(@AuthenticationPrincipal User user) {
        UserInfo userInfo = userService.userInfoFindByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }


    @PatchMapping("/user/register")
    public ResponseEntity<UserInfo> updateUser(@AuthenticationPrincipal User user, @RequestBody UserInfoUpdate userInfoUpdate) {
        UserInfo userInfo = userService.updateUserInfo(user, userInfoUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }


    @DeleteMapping("/user/delete")
    public ResponseEntity<Void> withdrawUser(@AuthenticationPrincipal User user) {
        jwtLoginService.withdrawUser(user) ;

        return new ResponseEntity(HttpStatus.OK);
    }



}
