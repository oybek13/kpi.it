package davr.team.dto.request;

import davr.team.entity.User;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Oybek Karimjanov
 * Date : 5.11.2022
 * Project Name : kpi.it
 */
@Data
@Builder
public class SignUpDto {
    private String name;
    private String username;
    private String email;
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
