//package core.gis;
//
//import core.util.GenericDao;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
///**
// * Created by Krzysztof on 2014-10-12.
// */
//@Component
//@Transactional(propagation = Propagation.REQUIRES_NEW)
//public class GeometryGisDao extends GenericDao {
//
//    private QGeometryGis qGeometryGis = QGeometryGis.geometryGis;
//
//    public List<GeometryGis> loadPointGises() {
//        return findAllNoFetch(null);
//    }
//
//    public void saveGeometryGIS(GeometryGis geometryGis) {
//        save(geometryGis);
//    }
//
//    public void saveGeometryGIS(List<GeometryGis> geometryGises) {
//        saveMany(geometryGises);
//    }
//
//}
