package core.tutorial;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Stock.class)
public abstract class Stock_ {

	public static volatile SingularAttribute<Stock, String> stockName;
	public static volatile SingularAttribute<Stock, Integer> stockId;
	public static volatile SetAttribute<Stock, StockDailyRecord> stockDailyRecords;
	public static volatile SetAttribute<Stock, Category> categories;
	public static volatile SingularAttribute<Stock, String> stockCode;

}

