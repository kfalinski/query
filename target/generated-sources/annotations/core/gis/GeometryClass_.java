package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Point;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GeometryClass.class)
public abstract class GeometryClass_ extends core.BaseEntity_ {

	public static volatile SingularAttribute<GeometryClass, Geometry> geometry;
	public static volatile SingularAttribute<GeometryClass, Point> location;

}

