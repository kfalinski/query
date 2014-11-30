package core.point;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Setter
@Getter
@Entity
public class GisPoint extends BaseEntity {
    private String name;
    private String code;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry pointJTS;

    public GisPoint() {
    }

    public GisPoint(String name, String code, Point point) {
        this.name = name;
        this.code = code;
        this.pointJTS = point;
    }
}
