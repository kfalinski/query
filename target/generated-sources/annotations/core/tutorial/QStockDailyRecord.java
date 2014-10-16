package core.tutorial;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QStockDailyRecord is a Querydsl query type for StockDailyRecord
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStockDailyRecord extends EntityPathBase<StockDailyRecord> {

    private static final long serialVersionUID = -425479691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStockDailyRecord stockDailyRecord = new QStockDailyRecord("stockDailyRecord");

    public final DatePath<java.util.Date> date = createDate("date", java.util.Date.class);

    public final NumberPath<Float> priceChange = createNumber("priceChange", Float.class);

    public final NumberPath<Float> priceClose = createNumber("priceClose", Float.class);

    public final NumberPath<Float> priceOpen = createNumber("priceOpen", Float.class);

    public final NumberPath<Integer> recordId = createNumber("recordId", Integer.class);

    public final QStock stock;

    public final NumberPath<Long> volume = createNumber("volume", Long.class);

    public QStockDailyRecord(String variable) {
        this(StockDailyRecord.class, forVariable(variable), INITS);
    }

    public QStockDailyRecord(Path<? extends StockDailyRecord> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockDailyRecord(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStockDailyRecord(PathMetadata<?> metadata, PathInits inits) {
        this(StockDailyRecord.class, metadata, inits);
    }

    public QStockDailyRecord(Class<? extends StockDailyRecord> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stock = inits.isInitialized("stock") ? new QStock(forProperty("stock")) : null;
    }

}

