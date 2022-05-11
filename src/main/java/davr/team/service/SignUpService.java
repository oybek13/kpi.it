package davr.team.service;

import davr.team.dto.SignUpDto;
import davr.team.dto.response.ApiResponse;
import davr.team.entity.Role;
import davr.team.entity.User;
import davr.team.dao.IRoleRepository;
import davr.team.dao.IUserRepository;
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
@Service
@RequiredArgsConstructor
public class SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final IUserRepository iUserRepository;

    private final IRoleRepository iRoleRepository;

    public ApiResponse registerUser(SignUpDto signUpDto){
        if (iUserRepository.existsByUsername(signUpDto.getUsername())){
            return new ApiResponse("This username is already existed!", false);
        }
        if (iUserRepository.existsByEmail(signUpDto.getEmail())){
            return new ApiResponse("This email is already existed!", false);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = iRoleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        iUserRepository.save(user);

        return new ApiResponse("User successfully registered!", true);
    }
}
