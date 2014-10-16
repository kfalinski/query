package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GeometryGis.class)
public abstract class GeometryGis_ extends core.BaseEntity_ {

	public static volatile SingularAttribute<GeometryGis, String> code;
	public static volatile SingularAttribute<GeometryGis, String> name;
	public static volatile SingularAttribute<GeometryGis, Geometry> geometry;

}

