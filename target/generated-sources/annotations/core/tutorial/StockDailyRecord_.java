package core.tutorial;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StockDailyRecord.class)
public abstract class StockDailyRecord_ {

	public static volatile SingularAttribute<StockDailyRecord, Integer> recordId;
	public static volatile SingularAttribute<StockDailyRecord, Float> priceChange;
	public static volatile SingularAttribute<StockDailyRecord, Long> volume;
	public static volatile SingularAttribute<StockDailyRecord, Date> date;
	public static volatile SingularAttribute<StockDailyRecord, Float> priceClose;
	public static volatile SingularAttribute<StockDailyRecord, Float> priceOpen;
	public static volatile SingularAttribute<StockDailyRecord, Stock> stock;

}

