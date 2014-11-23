package core.gis;


import com.vividsolutions.jts.geom.Geometry;
import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.postgis.Point;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
* Created by Krzysztof on 2014-10-11.
*/
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeometryClass extends BaseEntity {

    //    @ElementCollection//(targetClass = com.vividsolutions.jts.geom.Geometry.class)
//    private List<com.vividsolutions.jts.geom.Geometry> geometryVivid;
//    @OneToMany//(targetClass = org.postgis.Geometry.class)
//    private List<org.postgis.Geometry> geometryPostgGis;
//    @OneToMany//(targetClass = org.postgis.PGgeometry.class)
//    private List<org.postgis.PGgeometry> geometryGis;
//    @Type(type = "org.hibernate.spatial.GeometryType")
//    private Point location;

    private org.postgis.PGgeometry pgGeometry;
    private org.postgis.Point pointGis;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry geometry;
    @Column(name = "location")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;
//    @OneToMany
//    private List<com.vividsolutions.jts.geom.Point> pointVividList;
}
