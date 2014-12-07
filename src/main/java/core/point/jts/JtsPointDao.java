package core.point.jts;

import com.google.common.collect.Lists;
import com.mysema.query.jpa.JPQLQuery;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import core.point.legacy.LegacyPoint;
import core.utils.GenericDao;
import core.utils.GeoService;
import core.utils.PointToSaveBean;
import org.geolatte.geom.Point;
import org.geotools.geometry.jts.GeometryBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class JtsPointDao extends GenericDao {
    private static final QJtsPointEntity qJtsPointEntity = QJtsPointEntity.jtsPointEntity;
    private static final QJtsPointEntity qJtsPointEntityAlter = QJtsPointEntity.jtsPointEntity;

    public List<JtsPointEntity> loadJtsPoints() {
        return findAllNoFetch(qJtsPointEntity);
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
