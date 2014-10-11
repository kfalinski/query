package core.gis;



import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Krzysztof on 2014-10-11.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeometryClass extends BaseEntity {

    @ElementCollection(targetClass = com.vividsolutions.jts.geom.Geometry.class)
    private List<com.vividsolutions.jts.geom.Geometry> geometryVivid;
    @ElementCollection(targetClass = org.postgis.Geometry.class)
    private List<org.postgis.Geometry> geometryGis;
//    @OneToMany
//    private List<org.postgis.Point> pointGisList;
//    @OneToMany
//    private List<com.vividsolutions.jts.geom.Point> pointVividList;
}
