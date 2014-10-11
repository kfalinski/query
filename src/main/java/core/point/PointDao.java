package core.point;

import com.mysema.query.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Krzysztof on 2014-09-09.
 */

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PointDao {

    @PersistenceContext
    private EntityManager entityManager;

    private QPointCustom qPointCustom = QPointCustom.pointCustom;

    public void savePoint(PointCustom pointCustom) {
        entityManager.persist(pointCustom);
        entityManager.flush();
    }

    public void savePoints(List<PointCustom> pointCustomList) {
        for (PointCustom pointCustom : pointCustomList) {
            entityManager.persist(pointCustom);
        }
        entityManager.flush();
    }

    public List<PointCustom> loadPoints() {
        JPAQuery query = new JPAQuery(entityManager);
//        query=query.list()

        return query.from(qPointCustom).list(qPointCustom);
//        return (List<PointCustom>) entityManager.createQuery("from PointCustom").getResultList();
    }
}
