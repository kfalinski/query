package core.gis;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGeometryClass is a Querydsl query type for GeometryClass
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGeometryClass extends EntityPathBase<GeometryClass> {

    private static final long serialVersionUID = -1192695782L;

    public static final QGeometryClass geometryClass = new QGeometryClass("geometryClass");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    public final ComparablePath<com.vividsolutions.jts.geom.Geometry> geometry = createComparable("geometry", com.vividsolutions.jts.geom.Geometry.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final SimplePath<org.postgis.Point> location = createSimple("location", org.postgis.Point.class);

    public final SimplePath<org.postgis.PGgeometry> pgGeometry = createSimple("pgGeometry", org.postgis.PGgeometry.class);

    public final SimplePath<org.postgis.Point> pointGis = createSimple("pointGis", org.postgis.Point.class);

    public QGeometryClass(String variable) {
        super(GeometryClass.class, forVariable(variable));
    }

    public QGeometryClass(Path<? extends GeometryClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeometryClass(PathMetadata<?> metadata) {
        super(GeometryClass.class, metadata);
    }

}

