package core.point;

import core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
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
//    @Column(name = "geolattePoint")
//    @Convert( converter = PostgisConverter.class )
    private Point geolattePoint;

    public GeolattePointEntity() {
    }

    public GeolattePointEntity(String name, String code, Point point) {
        this.name = name;
        this.code = code;
        this.geolattePoint = point;
    }
}
