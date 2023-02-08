package bornlist.model;

import bornlist.entity.UnitEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SortUnit implements Comparable<SortUnit> {

    private Integer daysLeft = 0;
    private UnitEntity entity;


    @Override
    public int compareTo(SortUnit o) {
        return this.daysLeft.compareTo(o.daysLeft);
    }
}
