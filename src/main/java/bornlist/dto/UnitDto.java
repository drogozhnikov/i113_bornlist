package bornlist.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UnitDto {

    @NonNull
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    @NonNull
    private Date date;
    private String description;

}
