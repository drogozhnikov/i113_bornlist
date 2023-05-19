package bornlist.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    @NonNull
    private String regUser;
    @NonNull
    private String message;
}
