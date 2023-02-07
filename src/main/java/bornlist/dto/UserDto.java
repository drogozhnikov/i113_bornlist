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
    private Integer chatId;
    @NonNull
    private String userName;
    private String firstName;
    private String lastName;
}
