package core.gis;

import com.vividsolutions.jts.geom.Point;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.PGgeometry;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PointGis.class)
public abstract class PointGis_ extends core.BaseEntity_ {

	public static volatile SingularAttribute<PointGis, Point> pointVivid;
	public static volatile SingularAttribute<PointGis, PGgeometry> pGgeometry;

}

