package core.gis;

import com.mysema.query.spatial.path.GeometryPath;
import com.mysema.query.types.Predicate;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Created by Krzysztof on 2014-11-25.
 */
public class GeometryPredicates {

    public static Predicate lastNameIsLike(final Geometry geometry) {
        QGIS qgis = QGIS.gIS;
        return ((GeometryPath)qgis.geometry).in(geometry);
    }
}