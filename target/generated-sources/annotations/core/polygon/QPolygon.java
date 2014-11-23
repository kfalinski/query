package core.polygon;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPolygon is a Querydsl query type for Polygon
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPolygon extends EntityPathBase<Polygon> {

    private static final long serialVersionUID = -1521555433L;

    public static final QPolygon polygon = new QPolygon("polygon");

    public final NumberPath<Double> area = createNumber("area", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> length = createNumber("length", Double.class);

    public final StringPath name = createString("name");

    public final ListPath<core.point.PointCustom, core.point.QPointCustom> points = this.<core.point.PointCustom, core.point.QPointCustom>createList("points", core.point.PointCustom.class, core.point.QPointCustom.class, PathInits.DIRECT2);

    public QPolygon(String variable) {
        super(Polygon.class, forVariable(variable));
    }

    public QPolygon(Path<? extends Polygon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolygon(PathMetadata<?> metadata) {
        super(Polygon.class, metadata);
    }

}

