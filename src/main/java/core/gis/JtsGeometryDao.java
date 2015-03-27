package core.gis;

import com.mysema.query.jpa.JPQLQuery;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import core.point.jts.JtsPointEntity;
import core.utils.GenericDao;
import core.utils.PointToSaveBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Krzysztof on 2014-12-07.
 */
@Service
public class JtsGeometryDao extends GenericDao {
    private final static QJtsGeometryEntity qJtsGeometryEntity = QJtsGeometryEntity.jtsGeometryEntity;


    public List<JtsGeometryEntity> loadGeometries() {
        return findAllNoFetch(qJtsGeometryEntity);
    }

    public JtsGeometryEntity findByCode(String id) {
        JPQLQuery query = buildQuery(qJtsGeometryEntity);
        query = query.where(qJtsGeometryEntity.jtsGeometryEntity.code.eq(id));
        return query.singleResult(qJtsGeometryEntity);
    }

    @Transactional
    public void deleteAll() {
        removeAllEntities(qJtsGeometryEntity);
    }

    public void removeMany(List<JtsGeometryEntity> selectedJtsGeometries) {
        removeAllEntities(qJtsGeometryEntity);
    }
}
