package core.point;

import com.mysema.query.jpa.JPQLQuery;
import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class JtsPointDao extends GenericDao {
    private static final QJtsPointEntity qJtsPointEntity = QJtsPointEntity.jtsPointEntity;
    private static final QJtsPointEntity qJtsPointEntityAlter = QJtsPointEntity.jtsPointEntity;

    @Autowired
    private JtsPointBean jtsPointBean;

    public void loadGisPoints() {
        List<JtsPointEntity> allNoFetch = findAllNoFetch(qJtsPointEntity);
        jtsPointBean.setAllPoints(allNoFetch);
    }

    public List<JtsPointEntity> loadClose(double meters) {
        JPQLQuery query = buildQuery(qJtsPointEntityAlter);
        query = query.where(qJtsPointEntityAlter.id.eq(2L));
        JtsPointEntity jtsPointEntity = query.singleResult(qJtsPointEntityAlter);

        JPQLQuery query2 = buildQuery(qJtsPointEntity);
        query2 = query2.where(qJtsPointEntity.jtsPoint.distance(jtsPointEntity.getJtsPoint()).lt(meters));
        List<JtsPointEntity> jtsPointEntityList = query2.list(qJtsPointEntity);
        return jtsPointEntityList;
    }
}
