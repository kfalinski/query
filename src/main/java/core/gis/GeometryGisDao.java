package core.gis;


import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.spatial.path.GeometryPath;
import com.mysema.query.spatial.path.GeometryPaths;
import com.mysema.query.types.CollectionExpression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.ComparablePath;
import com.mysema.query.types.path.EnumPath;
import com.vividsolutions.jts.awt.PointShapeFactory;
import core.utils.GenericDao;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkt;
import org.postgis.LineString;
import org.postgis.LinearRing;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.sql.SQLException;
import java.util.List;

import static com.mysema.query.jpa.Conversions.convert;


/**
 * Created by Krzysztof on 2014-10-12.
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class GeometryGisDao extends GenericDao  {

    private QGeometryGis qGeometryGis = QGeometryGis.geometryGis;

    public List<GeometryGis> loadPointGises() {
        JPQLQuery query = buildQuery(qGeometryGis);
//        Geometry geometry = Wkt.fromWkt("LINESTRING(2 3,4 5,6 5,7 8)");
//        GeometryPath<Geometry> geometryPath = new GeometryPath(org.geolatte.geom.Geometry.class,
//                PathMetadataFactory.forProperty(QGeometryGis.geometryGis, qGeometryGis.geometryLatte.getMetadata().getName()));
//        query.where(geometryPath.intersects(geometry));
        return query.list(qGeometryGis);
    }
    public void saveGeometryGIS(GeometryGis geometryGis) {
        save(geometryGis);
    }

    public void saveGeometryGISes(List<GeometryGis> geometryGises) {
        saveMany(geometryGises);
    }
}
