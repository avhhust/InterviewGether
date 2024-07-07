package interviewgether.authserver.controller;

import interviewgether.authserver.dto.UserLoginDTO;
import interviewgether.authserver.dto.UserRegisterDTO;
import interviewgether.authserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO userLoginDTO){
//        Authentication authRequest =
//                UsernamePasswordAuthenticationToken.unauthenticated(userLoginDTO.getUsername(), userLoginDTO.getPassword());
//        Authentication authResponse =
//                this.authenticationManager.authenticate(authRequest);
//
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authResponse);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        userService.create(userRegisterDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping
//    public String
}
