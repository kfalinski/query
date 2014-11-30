package core.point;

import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Krzysztof on 2014-11-24.
 */
@Service
public class LegacyPointDao extends GenericDao {

    private static final QLegacyPoint Q_LEGACY_POINT = QLegacyPoint.legacyPoint;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteSelectedPoints(List<LegacyPoint> selectedPoints) {
        removeMany(Q_LEGACY_POINT, selectedPoints.stream().map(p -> p.getId()).collect(Collectors.toList()));
    }

    public void deleteAllPoints() {
        removeAllEntities(Q_LEGACY_POINT);
    }
}
