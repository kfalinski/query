package core.point.jts;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
public class JtsPointEntity extends BaseEntity {
    private static final long serialVersionUID = 65483473675794478L;

    private String name;
    private String code;
    private double z;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point jtsPoint;

    public JtsPointEntity(String name, String code, Point point, double z) {
        this.name = name;
        this.code = code;
        this.jtsPoint = point;
        this.z = z;
    }
}
