package davr.team.dto;

import lombok.Data;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
