package davr.team.service;

import davr.team.dto.request.SignUpDto;
import davr.team.entity.Role;
import davr.team.entity.User;
import davr.team.exception.ResourceAlreadyExistedException;
import davr.team.repository.RoleRepository;
import davr.team.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
@Builder
@Service
public class SignUpService {

    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public SignUpDto registerUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new ResourceAlreadyExistedException("This username is already existed!");
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new ResourceAlreadyExistedException("This email is already existed!");
        }

        Role roles = roleRepository.findByName("ROLE_USER").get();

        return SignUpDto.toDto(
                userRepository.save(User.builder()
                        .name(signUpDto.getName())
                        .username(signUpDto.getUsername())
                        .email(signUpDto.getEmail())
                        .password(passwordEncoder.encode(signUpDto.getPassword()))
                        .roles(Collections.singleton(roles))
                        .build()
                )
        );
    }

}
