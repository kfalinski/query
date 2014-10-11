package core.polyline;

import core.point.PointCustom;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Polyline.class)
public abstract class Polyline_ extends core.BaseEntity_ {

	public static volatile SingularAttribute<Polyline, String> name;
	public static volatile SingularAttribute<Polyline, Double> length;
	public static volatile ListAttribute<Polyline, PointCustom> points;

}

