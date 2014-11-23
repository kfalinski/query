package core.point;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPointCustom is a Querydsl query type for PointCustom
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPointCustom extends EntityPathBase<PointCustom> {

    private static final long serialVersionUID = -434010860L;

    public static final QPointCustom pointCustom = new QPointCustom("pointCustom");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final NumberPath<Double> x = createNumber("x", Double.class);

    public final NumberPath<Double> y = createNumber("y", Double.class);

    public final NumberPath<Double> z = createNumber("z", Double.class);

    public QPointCustom(String variable) {
        super(PointCustom.class, forVariable(variable));
    }

    public QPointCustom(Path<? extends PointCustom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointCustom(PathMetadata<?> metadata) {
        super(PointCustom.class, metadata);
    }

}

