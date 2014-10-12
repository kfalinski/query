//import com.mysema.query.jpa.impl.JPAQuery;
//import core.point.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.postgis.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by Krzysztof on 2014-10-12.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class pointServiceTest {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Mock
//    private PointsService pointsService;
//
//    @Mock
//    private PointDao pointDao;
//
//    @Mock
//    private PointBean pointBean;
//
//    @Test
//    public void testLoadPoint() {
//        PointBean pointBean = new PointBean();
//        UUID uuid = UUID.randomUUID();
//        String name = uuid.toString();
//        String randomCode = uuid.toString().substring(0, 5);
//        PointCustom pointCustom = new PointCustom(name, randomCode, 12.12, 13.13, 14.14);
//        entityManager.persist(pointCustom);
//        List<PointCustom> pointCustomList = loadPoints();
//        assertTrue(pointCustomList.contains(pointCustom));
//        PGgeometry geom = (PGgeometry)r.getObject(1);
//        if( geom.getType() == Geometry.POLYGON ) {
//            Polygon pl = (Polygon)geom.getGeometry();
//            for( int r = 0; r < pl.numRings(); r++) {
//                LinearRing rng = pl.getRing(r);
//                System.out.println("Ring: " + r);
//                for( int p = 0; p < rng.numPoints(); p++ ) {
//                    Point pt = rng.getPoint(p);
//                    System.out.println("Point: " + p);
//                    System.out.println(pt.toString());
//                }
//            }
//        }
//    }
//
//    private List<PointCustom> loadPoints() {
//        JPAQuery query = new JPAQuery(entityManager);
//        query=query.list()
//
//        QPointCustom qPointCustom = QPointCustom.pointCustom;
//        return query.from(qPointCustom).list(qPointCustom);
//        return (List<PointCustom>) entityManager.createQuery("from PointCustom").getResultList();
//    }
//    private LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }
//}
