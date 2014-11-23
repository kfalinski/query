//package core.gis;
//
//import com.vividsolutions.jts.geom.Geometry;
//import core.BaseEntity;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.Entity;
//
///**
// * Created by Krzysztof on 2014-11-23.
// */
//@Setter
//@Getter
//@Entity
//public class GeometryGis extends BaseEntity {
//    private static final long serialVersionUID = 3753316554003153534L;
//    private String dupa;
//    @Type(type = "org.hibernate.spatial.GeometryType")
//    private Geometry geometry;
//
//    public GeometryGis() {
//    }
//
//    public GeometryGis(String wktName, String wktCode, Geometry geom) {
//        setName(wktName);
//        setCode(wktCode);
//        geometry = geom;
//    }
//}
