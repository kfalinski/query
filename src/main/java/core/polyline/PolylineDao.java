package core.polyline;

import com.google.common.collect.Lists;
import core.point.PointCustom;
import core.point.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
* Created by Krzysztof on 2014-10-04.
*/
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PolylineDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PolylineBean polylineBean;

    public List<Polyline> loadPolylines() {
        return (List<Polyline>) entityManager.createQuery("from Polyline").getResultList();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePolylile(Polyline polyline) {
        List<PointCustom> customList = polylineBean.getAllPoints();
        polyline.setPoints(customList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= customList.size()-1; i++) {
            sb.append(customList.get(i).getName());
            if (i < customList.size()) {
                sb.append("_");
            }
        }
        polyline.setLength(polylineBean.getLength());
        polyline.setName(sb.toString());
        entityManager.merge(polyline);
        entityManager.flush();
        List<PointCustom> pointCustomList = Lists.newArrayList();
        polylineBean.setAllPoints(pointCustomList);
        polylineBean.setLength(0.0);
    }

}
