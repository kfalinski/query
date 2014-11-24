package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-11-23.
 */
@Setter
@Getter
@Entity
public class GeometryGis extends BaseEntity {
    private static final long serialVersionUID = 3753316554003153534L;
    private String name;
    private String code;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geometry;

}
