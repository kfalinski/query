package core.point;

import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class JtsPointDao extends GenericDao {
    private static final QJtsPointEntity Q_JTS_POINT = QJtsPointEntity.jtsPointEntity;

    @Autowired
    private JtsPointBean jtsPointBean;

    public void loadGisPoints() {
        List<JtsPointEntity> allNoFetch = findAllNoFetch(Q_JTS_POINT);
        jtsPointBean.setAllPoints(allNoFetch);
    }
}
