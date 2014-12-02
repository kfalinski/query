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
public class GeolattePointDao extends GenericDao {
    private static final QGeolattePointEntity qGeolattePoint = QGeolattePointEntity.geolattePointEntity;
    private static final QGeolattePointEntity qGeolattePointAlter = new QGeolattePointEntity("geolatteAlter");

    @Autowired
    private GeolatteBean geolatteBean;

    public void loadGisPoints() {
        List<GeolattePointEntity> allNoFetch = findAllNoFetch(qGeolattePoint);
        geolatteBean.setAllPoints(allNoFetch);
    }


    public List<GeolattePointEntity> loadClose(double meters) {
        JPQLQuery query = buildQuery(qGeolattePointAlter);
        query = query.where(qGeolattePointAlter.id.eq(2L));
        GeolattePointEntity geolattePointEntity = query.singleResult(qGeolattePointAlter);

        JPQLQuery query2 = buildQuery(qGeolattePoint);
        query2 = query2.where(qGeolattePoint.geolattePoint.distance(geolattePointEntity.getGeolattePoint()).lt(meters));
        List<GeolattePointEntity> geolattePointEntityList = query2.list(qGeolattePoint);
        return geolattePointEntityList;
    }
}
