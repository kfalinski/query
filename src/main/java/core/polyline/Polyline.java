package core.polyline;

import core.BaseEntity;
import core.point.PointCustom;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Krzysztof on 2014-10-02.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Polyline extends BaseEntity implements Serializable {

    private String name;

    private Double length;

    @OneToMany
    private List<PointCustom> points ;


}
