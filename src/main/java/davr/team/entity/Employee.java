package davr.team.entity;

import davr.team.enums.Level;
import davr.team.enums.PlasticType;
import davr.team.enums.Position;
import davr.team.entity.template.MainEntity;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Oybek Karimjanov
 * Date : 5.12.2022
 * Project Name : kpi.it
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends MainEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_day")
    private String birthDay;

    @Column(name = "passport_serial")
    private String passSerial;

    @Column(name = "passport_number")
    private int passNumber;

    @Enumerated(EnumType.STRING)
    private PlasticType cardHolder;

    @Column(name = "nci_number")
    private int nciNumber;

    @Column(name = "salary")
    private long salary;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "active")
    private Boolean active;


}
