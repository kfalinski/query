package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class GIS {



    // The @Type annotation is Hibernate specific, and the only non-JPA annotation that is required.
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geometry;


}