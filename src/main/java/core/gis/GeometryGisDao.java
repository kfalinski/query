package core.gis;


import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.spatial.path.GeometryPath;
import com.mysema.query.spatial.path.GeometryPaths;
import com.mysema.query.types.CollectionExpression;
import com.mysema.query.types.path.ComparablePath;
import com.vividsolutions.jts.awt.PointShapeFactory;
import core.utils.GenericDao;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import org.geolatte.geom.codec.Wkt;
import org.postgis.LineString;
import org.postgis.LinearRing;
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
public class GeometryGisDao extends GenericDao {

    private QGeometryGis qGeometryGis = QGeometryGis.geometryGis;


    public List<GeometryGis> loadPointGises() {
        JPQLQuery query = buildQuery(qGeometryGis);
        Geometry geometry = Wkt.fromWkt("LINESTRING(2 3,4 5,6 5,7 8)");
        GeometryGis geometryGis = new GeometryGis();
        try {
            org.postgis.Geometry geometryPOSTGIS = new LineString("LINESTRING(2 3,4 5,6 5,7 8)");
            geometryGis.setGeometryPOSTGIS(geometryPOSTGIS);
            save(geometryGis);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        geometryGis.setGeometryPOSTGIS(PGGeTyConvegeometry);

//        GeometryPath<Geometry> geometryPath = qGeometryGis.geometryLatte;
//        query.where(qGeometryGis.geometryJTS.)
//        query.where(geometry.)
//        query.where(qGeometryGis.geometryLatte.)
//query=query.where(qGeometryGis.geometryLatte.)
//        query = query.leftJoin( qGeometryGis.geometryLatte);
//        GeometryPath<Geometry> geometryPaths = qGeometryGis.geometryLatte;
//        query=query.where(geometryPaths.)
//        GeometryPath<Geometry> geometryPath = qGeometryGis.geometryLatte;
        return findAllNoFetch(null);
    }

    public void saveGeometryGIS(GeometryGis geometryGis) {
        save(geometryGis);
    }

    public void saveGeometryGIS(List<GeometryGis> geometryGises) {
        saveMany(geometryGises);
    }
}
