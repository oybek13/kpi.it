package davr.team.controller;

import davr.team.dto.request.LoginDto;
import davr.team.dto.request.SignUpDto;
import davr.team.dto.response.JwtAuthResponse;
import davr.team.service.LoginService;
import davr.team.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<JwtAuthResponse> authenticate(@Valid @RequestBody LoginDto loginDto){
        return ResponseEntity.ok(loginService.login(loginDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(signUpService.registerUser(signUpDto));
    }







}
