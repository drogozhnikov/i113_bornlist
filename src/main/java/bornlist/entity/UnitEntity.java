package bornlist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "units")
public class UnitEntity  implements Comparable<UnitEntity>{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private Date date;
    private boolean notify;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Override
    public int compareTo(UnitEntity o) {
        if(firstName.equals(o.getFirstName())&&lastName.equals(o.lastName)&&date.getTime()==o.getDate().getTime()){
            return 0;
        }else{
            return 1;
        }
    }
}
