package core.gis;

/**
* Created by Krzysztof on 2014-10-12.
*/

import com.vividsolutions.jts.geom.Geometry;
import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SomeGeographicClass extends BaseEntity {

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geometry;

}