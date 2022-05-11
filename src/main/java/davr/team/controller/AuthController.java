package davr.team.controller;

import davr.team.dto.LoginDto;
import davr.team.dto.SignUpDto;
import davr.team.dto.response.ApiResponse;
import davr.team.dto.response.JwtAuthResponse;
import davr.team.security.JwtTokenProvider;
import davr.team.service.LoginService;
import davr.team.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(loginService.login(loginDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        ApiResponse apiResponse = signUpService.registerUser(signUpDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }





}
