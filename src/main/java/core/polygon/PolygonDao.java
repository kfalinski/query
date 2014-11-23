package core.polygon;

import com.google.common.collect.Lists;
import core.point.PointCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Krzysztof on 2014-10-02.
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PolygonDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private PolygonBean polygonBean;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePolygon(Polygon polygon) {

        List<PointCustom> customList = polygonBean.getAllPoints();
        polygon.setPoints(customList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= customList.size() - 1; i++) {
            sb.append(customList.get(i).getName());
            if (i < customList.size()) {
                sb.append("_");
            }
        }
        polygon.setLength(polygonBean.getLength());
        polygon.setName(sb.toString());
        polygon.setArea(polygonBean.getArea());
        entityManager.merge(polygon);
        entityManager.flush();
        List<PointCustom> pointCustomList = Lists.newArrayList();
        polygonBean.setAllPoints(pointCustomList);
        polygonBean.setLength(0.0);
        polygonBean.setArea(0.0);
    }


    public List<Polygon> loadPolygons() {
        return (List<Polygon>) entityManager.createQuery("from Polygon").getResultList();
    }
}

