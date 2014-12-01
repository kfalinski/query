package core.point;

import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class GeolattePointDao extends GenericDao {
    private static final QGeolattePointEntity Q_GEOLATTE_POINT = QGeolattePointEntity.geolattePointEntity;

    @Autowired
    private GeolatteBean geolatteBean;

    public void loadGisPoints() {
        List<GeolattePointEntity> allNoFetch = findAllNoFetch(Q_GEOLATTE_POINT);
        geolatteBean.setAllPoints(allNoFetch);
    }
}
