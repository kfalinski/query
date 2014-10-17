package core.gis;

import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Krzysztof on 2014-10-12.
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class GeometryGisDao {

    @PersistenceContext
    private EntityManager entityManager;

    private QGeometryGis qGeometryGis = QGeometryGis.geometryGis;

    public List<GeometryGis> loadPointGises() {
        JPAQuery query = new JPAQuery(entityManager);
//        query=query.list()

        return query.from(qGeometryGis).list(qGeometryGis);
    }

    public void saveGeometryGIS(GeometryGis geometryGis) {
        entityManager.persist(geometryGis);
        entityManager.flush();

    }

    public void saveGeometryGIS(List<GeometryGis> geometryGis) {
        for (GeometryGis geometry : geometryGis) {
            entityManager.persist(geometry);
        }
        entityManager.flush();

    }

//    public void savePoints(List<Point> pointList) {
//        for (Point point : pointList) {
//            PointGis pointGis = new PointGis();
//            pointGis.setPointPG(point);
//            entityManager.persist(pointGis);
//            entityManager.flush();
//        }
//
//    }


}
