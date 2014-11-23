package core.polygon;

import core.point.PointCustom;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Polygon.class)
public abstract class Polygon_ {

	public static volatile SingularAttribute<Polygon, Double> area;
	public static volatile SingularAttribute<Polygon, Double> length;
	public static volatile SingularAttribute<Polygon, String> name;
	public static volatile SingularAttribute<Polygon, Integer> id;
	public static volatile ListAttribute<Polygon, PointCustom> points;

}

