package core.point.geoltte;

import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class GeolattePointDao extends GenericDao {
    private static final QGeolattePointEntity qGeolattePointEntity = QGeolattePointEntity.geolattePointEntity;
    private static final QGeolattePointEntity qGeolattePointAlter = new QGeolattePointEntity("geolatteAlter");
    @Autowired
    private GeolatteBean geolatteBean;

    @Autowired
    private DataSource dataSource;

    public void loadGisPoints() {
        List<GeolattePointEntity> allNoFetch = findAllNoFetch(qGeolattePointEntity);
        geolatteBean.setAllPoints(allNoFetch);
    }

    @Transactional
    public void saveManyGeolatte() {


//        GeolattePointEntity geolattePointEntity = new GeolattePointEntity();
//        org.geolatte.geom.Point point = new org.geolatte.geom.Point(Points.create3D(2.0, 2.3, 4.3));
//        geolattePointEntity.setGeolattePoint(point);
//        geolattePointEntity.setName("name");
//        SQLTemplates templates = new PostGISTemplates();
//        Configuration configuration = new Configuration(templates);
//        SQLQueryFactory factory = new SQLQueryFactory(configuration, dataSource);
//        SQLInsertClause insert = factory.insert(qSpatial);
//        insert.populate(geolattePointEntity).execute();
//
//        SQLQuery query = factory.query();
//        List<QGeolattepointentity> list = query.from(qSpatial).list(qSpatial);
//        QGeolattepointentity qGeolattepointentity = list.get(0);
//
//        System.out.println(qGeolattepointentity);
//
//        JPQLQuery query1 = buildQuery(qGeolattePointEntity);
//        List<GeolattePointEntity> list1 = query1.list(qGeolattePointEntity);
//        System.out.println(list1);
//        Map<String,Object> params = new HashMap<String,Object>();
//        params.put( "dbtype", "postgis");
//        params.put( "host", "localhost");
//        params.put( "port", 5432);
//        params.put( "schema", "public");
//        params.put( "database", "baza");
//        params.put( "user", "postgres");
//        params.put( "passwd", "123");
//
//        try {
//            DataStore dataStore= DataStoreFinder.getDataStore(params);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//
//

//
//        List<GeolattePointEntity> geolattePointEntities = query.list(qGeolattePoint.geolattePoint);
//
//        <property name="driverClassName" value="org.postgresql.Driver"/>
//        <property name="url" value="jdbc:postgres://localhost:5432/baza"/>
//        <property name="username" value="postgres"/>
//        <property name="password" value="123"/>


//        SQLInsertClause query = new SQLInsertClause(new java.sql.Connection() {
//        }configuration);
//        new SQLInsertClause()
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Point point = new Point();
//        point.x = 2;
//        point.y = 3;
//        point.z = 4;

//        PGgeometry geometry = new PGgeometry(point);
//            mPreparedStatementInsertObservation.setObject(1, geometry);
//            Connection mConnection = DriverManager.getConnection("jdbc:postgres://localhost:5432/baza", "postgres", "123");
//            ((org.postgresql.PGConnection) mConnection).addDataType("geometry",org.postgis.PGgeometry.class);
//            mPreparedStatementInsertObservation = mConnection.prepareStatement(INSERT_OBSERVATION_SQL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }


//    public List<GeolattePointEntity> loadClose(double meters) {
//        SQLTemplates templates = new PostGISTemplates();
//        Configuration configuration = new Configuration(templates);
//        SQLQuery query = new SQLQuery(configuration);
//        query = query.where(qGeolattePointAlter.id.eq(2L));
//        GeolattePointEntity geolattePointEntity = query.singleResult(qGeolattePointAlter);

//        JPQLQuery query2 = buildQuery(qGeolattePoint);
//        query2 = query2.where(qGeolattePoint.geolattePoint.distance(geolattePointEntity.getGeolattePoint()).lt(meters));
//        List<GeolattePointEntity> geolattePointEntityList = query2.list(qGeolattePoint);
//        return geolattePointEntityList;
//    }
}
