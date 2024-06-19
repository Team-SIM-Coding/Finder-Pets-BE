package inf.saveanimals.controller.login;

import inf.saveanimals.config.login.jwt.JwtProperties;
import inf.saveanimals.domain.users.User;
import inf.saveanimals.request.users.LoginRequest;
import inf.saveanimals.request.users.UserCreate;
import inf.saveanimals.request.users.UserEdit;
import inf.saveanimals.response.users.LoginUserResponse;
import inf.saveanimals.response.users.UserTokenDto;
import inf.saveanimals.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/checkId")
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

        return ResponseEntity.status(HttpStatus.OK).header(loginDTO.getToken()).body(loginDTO);
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


}
