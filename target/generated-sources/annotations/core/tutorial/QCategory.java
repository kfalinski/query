package core.tutorial;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -423225057L;

    public static final QCategory category = new QCategory("category");

    public final NumberPath<Integer> categoryId = createNumber("categoryId", Integer.class);

    public final StringPath desc = createString("desc");

    public final StringPath name = createString("name");

    public final SetPath<Stock, QStock> stocks = this.<Stock, QStock>createSet("stocks", Stock.class, QStock.class, PathInits.DIRECT2);

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata<?> metadata) {
        super(Category.class, metadata);
    }

}

