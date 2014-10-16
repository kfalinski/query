package core.gis;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPointGis is a Querydsl query type for PointGis
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPointGis extends EntityPathBase<GeometryGis> {

    private static final long serialVersionUID = -1494995027L;

    public static final QPointGis pointGis = new QPointGis("pointGis");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ComparablePath<com.vividsolutions.jts.geom.Point> pointVivid = createComparable("pointVivid", com.vividsolutions.jts.geom.Point.class);

    public QPointGis(String variable) {
        super(GeometryGis.class, forVariable(variable));
    }

    public QPointGis(Path<? extends GeometryGis> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointGis(PathMetadata<?> metadata) {
        super(GeometryGis.class, metadata);
    }

}

