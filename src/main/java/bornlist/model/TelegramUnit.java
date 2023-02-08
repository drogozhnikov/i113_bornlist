package bornlist.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramUnit {

    @NonNull
    private String chatId;
    @NonNull
    private String userName;
    private String message;
    private String firstName;
    private String lastName;


}
