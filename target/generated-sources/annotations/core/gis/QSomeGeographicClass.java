package core.gis;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSomeGeographicClass is a Querydsl query type for SomeGeographicClass
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSomeGeographicClass extends EntityPathBase<SomeGeographicClass> {

    private static final long serialVersionUID = -1509135711L;

    public static final QSomeGeographicClass someGeographicClass = new QSomeGeographicClass("someGeographicClass");

    public final core.QBaseEntity _super = new core.QBaseEntity(this);

    public final ComparablePath<com.vividsolutions.jts.geom.Geometry> geometry = createComparable("geometry", com.vividsolutions.jts.geom.Geometry.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QSomeGeographicClass(String variable) {
        super(SomeGeographicClass.class, forVariable(variable));
    }

    public QSomeGeographicClass(Path<? extends SomeGeographicClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSomeGeographicClass(PathMetadata<?> metadata) {
        super(SomeGeographicClass.class, metadata);
    }

}

