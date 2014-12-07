package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-12-07.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class JtsGeometryEntity extends BaseEntity {

    private String name;
    private String code;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geometry;

    public JtsGeometryEntity(String wktName, String wktCode, Geometry geom) {
        this.name = wktName;
        this.code = wktCode;
        this.geometry = geom;
    }
}
