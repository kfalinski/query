package core.point;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PointCustom.class)
public abstract class PointCustom_ extends core.BaseEntity_ {

	public static volatile SingularAttribute<PointCustom, String> code;
	public static volatile SingularAttribute<PointCustom, String> name;
	public static volatile SingularAttribute<PointCustom, Double> x;
	public static volatile SingularAttribute<PointCustom, Double> y;
	public static volatile SingularAttribute<PointCustom, Double> z;

}

