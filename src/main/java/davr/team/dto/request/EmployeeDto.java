package davr.team.dto.request;

import davr.team.entity.Employee;
import davr.team.enums.Level;
import davr.team.enums.PlasticType;
import davr.team.enums.Position;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
@Data
@Builder
public class EmployeeDto {

    private long id;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String passportSerial;
    private int passportNumber;
    private PlasticType plasticType;
    private int nciNumber;
    private long salary;
    private Position position;
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
