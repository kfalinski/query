package core.gis;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGeometryClass is a Querydsl query type for GeometryClass
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGeometryClass extends EntityPathBase<GeometryClass> {

    private static final long serialVersionUID = -1192695782L;

    public static final QGeometryClass geometryClass = new QGeometryClass("geometryClass");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    public final ListPath<org.postgis.Geometry, SimplePath<org.postgis.Geometry>> geometryGis = this.<org.postgis.Geometry, SimplePath<org.postgis.Geometry>>createList("geometryGis", org.postgis.Geometry.class, SimplePath.class, PathInits.DIRECT2);

    public final ListPath<com.vividsolutions.jts.geom.Geometry, ComparablePath<com.vividsolutions.jts.geom.Geometry>> geometryVivid = this.<com.vividsolutions.jts.geom.Geometry, ComparablePath<com.vividsolutions.jts.geom.Geometry>>createList("geometryVivid", com.vividsolutions.jts.geom.Geometry.class, ComparablePath.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

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

