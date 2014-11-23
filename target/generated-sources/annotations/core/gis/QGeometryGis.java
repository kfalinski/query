package core.gis;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QGeometryGis is a Querydsl query type for GeometryGis
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGeometryGis extends EntityPathBase<GeometryGis> {

    private static final long serialVersionUID = -756543757L;

    public static final QGeometryGis geometryGis = new QGeometryGis("geometryGis");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    public final StringPath code = createString("code");

    public final ComparablePath<com.vividsolutions.jts.geom.Geometry> geometry = createComparable("geometry", com.vividsolutions.jts.geom.Geometry.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QGeometryGis(String variable) {
        super(GeometryGis.class, forVariable(variable));
    }

    public QGeometryGis(Path<? extends GeometryGis> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeometryGis(PathMetadata<?> metadata) {
        super(GeometryGis.class, metadata);
    }

}

