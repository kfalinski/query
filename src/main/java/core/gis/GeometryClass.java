package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Krzysztof on 2014-10-11.
 */
@Entity
public class GeometryClass extends BaseEntity {

    @Id


    private Geometry geometry;

}
