package bornlist.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UnitDto implements Comparable<UnitDto> {

    private int id;
    @NonNull
    private String userName = "drogozhnikov";
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Date date;

    private boolean notify;

    private String description;

    private String daysLeft;

    @Override
    public int compareTo(UnitDto o) {
        return this.daysLeft.compareTo(o.daysLeft);
    }
}
