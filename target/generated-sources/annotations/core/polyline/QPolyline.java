package core.polyline;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPolyline is a Querydsl query type for Polyline
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPolyline extends EntityPathBase<Polyline> {

    private static final long serialVersionUID = -947481153L;

    public static final QPolyline polyline = new QPolyline("polyline");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> length = createNumber("length", Double.class);

    public final StringPath name = createString("name");

    public final ListPath<core.point.PointCustom, core.point.QPointCustom> points = this.<core.point.PointCustom, core.point.QPointCustom>createList("points", core.point.PointCustom.class, core.point.QPointCustom.class, PathInits.DIRECT2);

    public QPolyline(String variable) {
        super(Polyline.class, forVariable(variable));
    }

    public QPolyline(Path<? extends Polyline> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolyline(PathMetadata<?> metadata) {
        super(Polyline.class, metadata);
    }

}

