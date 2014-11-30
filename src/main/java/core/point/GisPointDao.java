package core.point;

import com.mysema.query.jpa.JPQLQuery;
import core.utils.GenericDao;
import org.geolatte.geom.Point;
import org.geolatte.geom.PointSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class GisPointDao extends GenericDao {
    private static final QGisPoint Q_GIS_POINT = QGisPoint.gisPoint;

    @Autowired
    private GisPointBean gisPointBean;

    public void loadGisPoints() {

        List<GisPoint> allNoFetch = findAllNoFetch(Q_GIS_POINT);
        gisPointBean.setAllPoints(allNoFetch);
    }
    public void dupa(){
   
    }

}
