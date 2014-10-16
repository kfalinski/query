package core.tutorial;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStock is a Querydsl query type for Stock
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStock extends EntityPathBase<Stock> {

    private static final long serialVersionUID = -749930859L;

    public static final QStock stock = new QStock("stock");

    public final SetPath<Category, QCategory> categories = this.<Category, QCategory>createSet("categories", Category.class, QCategory.class, PathInits.DIRECT2);

    public final StringPath stockCode = createString("stockCode");

    public final SetPath<StockDailyRecord, QStockDailyRecord> stockDailyRecords = this.<StockDailyRecord, QStockDailyRecord>createSet("stockDailyRecords", StockDailyRecord.class, QStockDailyRecord.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stockId = createNumber("stockId", Integer.class);

    public final StringPath stockName = createString("stockName");

    public QStock(String variable) {
        super(Stock.class, forVariable(variable));
    }

    public QStock(Path<? extends Stock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStock(PathMetadata<?> metadata) {
        super(Stock.class, metadata);
    }

}

