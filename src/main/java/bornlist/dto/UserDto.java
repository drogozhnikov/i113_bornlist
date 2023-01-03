package bornlist.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    @NonNull
    private Integer id;
    @NonNull
    private String userName;
    @NonNull
    private String telegramId;
}
