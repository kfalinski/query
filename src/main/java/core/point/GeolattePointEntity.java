package core.point;

import core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-12-01.
 */
@Setter
@Getter
@Entity
public class GeolattePointEntity extends BaseEntity {

    private String name;
    private String code;

    private Geometry geolattePoint;

    public GeolattePointEntity() {
    }

    public GeolattePointEntity(String name, String code, Point point) {
        this.name = name;
        this.code = code;
        this.geolattePoint = point;
    }
}
