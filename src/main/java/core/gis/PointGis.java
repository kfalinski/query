package core.gis;

import com.vividsolutions.jts.geom.Point;
import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.postgis.PGgeometry;

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
public class PointGis extends BaseEntity {
    @Column(name = "pointVivid")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point pointVivid;
//    @Column(name = "pointPG", nullable = true, columnDefinition = "Geometry")
//    private org.postgis.Point pointPG;
//    @Column(name = "pgGeometry")
//    @Type(type = "org.hibernate.spatial.GeometryType")
//    private PGgeometry pGgeometry;

    public void cos() {

    }
}
