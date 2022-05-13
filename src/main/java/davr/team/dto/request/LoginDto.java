package davr.team.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
@Data
public class LoginDto {

    @NotBlank(message = "Имя пользователя или адрес электронной почты не должны быть пустыми")
    private String usernameOrEmail;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;
}
