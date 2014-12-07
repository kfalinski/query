package core.polyline;

import com.vividsolutions.jts.geom.MultiPoint;
import core.BaseEntity;
import core.point.jts.JtsPointEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Krzysztof on 2014-12-06.
 */
@Entity
@Getter
@Setter
public class JtsPolyline extends BaseEntity {
    @OneToMany
    private Set<JtsPointEntity> jtsPoints;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private MultiPoint jtsMultipoint;

}
