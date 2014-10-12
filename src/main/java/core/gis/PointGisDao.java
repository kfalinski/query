package core.gis;

import com.google.common.collect.Lists;
import com.mysema.query.jpa.impl.JPAQuery;
import org.postgis.Point;
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
public class PointGisDao {

    @PersistenceContext
    private EntityManager entityManager;

    private QPointGis qPointGis=QPointGis.pointGis;

    public List<PointGis> loadPointGises(){
        JPAQuery query = new JPAQuery(entityManager);
//        query=query.list()

        return query.from(qPointGis).list(qPointGis);
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
