package davr.team.dto.request;

import davr.team.entity.Employee;
import davr.team.enums.Level;
import davr.team.enums.PlasticType;
import davr.team.enums.Position;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
@Data
@Builder
@Validated
public class EmployeeDto {

    private long id;

    @NotBlank(message = "Имя должно содержать от 3 до 20 символов!")
    @Size(max = 20, min = 3)
    private String firstName;

    @NotBlank(message = "Фамилия должна содержать от 5 до 30 символов!")
    @Size(max = 30, min = 5)
    private String lastName;

    @NotBlank(message = "Укажите день рождения!")
    private String birthDay;

    @NotBlank
    @Size(min = 2, message = "Серия паспорта должен состоять не менее 2 символов!")
    private String passportSerial;

    @NotNull(message = "Серийный номер паспорта должен состоять не менее 7 символов!")
    private Integer passportNumber;

    @NotNull(message = "Тип пластика должен быть только HUMO или UZCARD!")
    private PlasticType plasticType;

    @NotNull(message = "Уникальный номер должен быть не менее 8 символов!")
    private Integer nciNumber;

    @NotNull(message = "Зарплата не должна быть пустой!")
    private Long salary;

    @NotNull(message = "Необходимо указать позицию!")
    private Position position;

    @NotNull(message = "Уровень должен быть INTERN, JUNIOR, MIDDLE, SENIOR соответственно!")
    private Level level;

    private boolean active;

    public static EmployeeDto toDto(Employee e){
        return EmployeeDto.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .birthDay(e.getBirthDay())
                .passportSerial(e.getPassSerial())
                .passportNumber(e.getPassNumber())
                .plasticType(e.getCardHolder())
                .nciNumber(e.getNciNumber())
                .salary(e.getSalary())
                .position(e.getPosition())
                .level(e.getLevel())
                .active(e.getActive())
                .build();
    }





}
