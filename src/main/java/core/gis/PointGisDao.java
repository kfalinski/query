package core.gis;

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

    public void savePoints(List<Point> pointList) {
        for (Point point : pointList) {
            PointGis pointGis = new PointGis();
            pointGis.setPointPG(point);
            entityManager.persist(pointGis);
            entityManager.flush();
        }

    }


}
