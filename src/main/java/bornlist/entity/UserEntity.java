package bornlist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chatId;
    private String userName;
    private String firstName;
    private String lastName;

    public UserEntity(String chatId, String userName, String firstName, String lastName){
        this.chatId = chatId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
