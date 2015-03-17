package core.point.geoltte;

import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class GeolattePointDao extends GenericDao {
    private static final QGeolattePointEntity qGeolattePointEntity = QGeolattePointEntity.geolattePointEntity;
    private static final QGeolattePointEntity qGeolattePointAlter = new QGeolattePointEntity("geolatteAlter");
    @Autowired
    private GeolatteBean geolatteBean;

    @Autowired
    private DataSource dataSource;

    public void loadGisPoints() {
        List<GeolattePointEntity> allNoFetch = findAllNoFetch(qGeolattePointEntity);
        geolatteBean.setAllPoints(allNoFetch);
    }
}
