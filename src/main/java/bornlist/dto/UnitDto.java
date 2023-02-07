package bornlist.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UnitDto {

    @NonNull
    private Integer id;
    @NonNull
    private Integer userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Date date;

}
