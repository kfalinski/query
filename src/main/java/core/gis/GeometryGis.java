package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-10-12.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeometryGis extends BaseEntity {

    private String name;
    private String code;

    @Column(name = "geometry")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geometry;

}
