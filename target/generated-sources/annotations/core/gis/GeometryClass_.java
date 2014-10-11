package core.gis;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GeometryClass.class)
public abstract class GeometryClass_ extends core.BaseEntity_ {

	public static volatile ListAttribute<GeometryClass, Geometry> geometryGis;
	public static volatile ListAttribute<GeometryClass, com.vividsolutions.jts.geom.Geometry> geometryVivid;

}

