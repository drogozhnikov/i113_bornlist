package bornlist.dto;

import bornlist.entity.UnitEntity;
import bornlist.entity.UserEntity;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BindDto {

    @NonNull
    private UserEntity userEntity;
    private List<UnitEntity> unitEntity;

}
