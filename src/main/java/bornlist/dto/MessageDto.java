package bornlist.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    @NonNull
    private String userName;
    @NonNull
    private String message;
}
