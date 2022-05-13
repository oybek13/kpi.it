package davr.team.dto.request;

import davr.team.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
@Data
@Builder
public class SignUpDto {

    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @NotBlank(message = "Электронная почта не должна быть пустой")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    public static SignUpDto toDto(User u){
        return SignUpDto.builder()
                .name(u.getEmail())
                .username(u.getUsername())
                .email(u.getEmail())
                .password(u.getPassword())
                .build();
    }
}
