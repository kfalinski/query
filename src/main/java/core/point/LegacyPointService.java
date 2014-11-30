package core.point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class LegacyPointService {

    @Autowired
    private LegacyPointBean legacyPointBean;

    @Autowired
    private LegacyPointDao legacyPointDao;

    private static final QLegacyPoint Q_LEGACY_POINT = QLegacyPoint.legacyPoint;

    public void loadLegacyPoints() {
        List<LegacyPoint> legacyPointList = legacyPointDao.findAllNoFetch(Q_LEGACY_POINT);
        legacyPointBean.setAllPoints(legacyPointList);
    }

    @Transactional
    public void deleteSelectedPoints() {
        legacyPointDao.deleteSelectedPoints(legacyPointBean.getSelectedPoints());
    }

    public void deleteAllPoints() {
        legacyPointDao.deleteAllPoints();
    }

    public void populatePolygonBean() {
    }

    public void populatePolylineBean() {
    }
}
