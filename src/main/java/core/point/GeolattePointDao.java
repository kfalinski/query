package core.point;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.spatial.PostGISTemplates;
import core.utils.GenericDao;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class GeolattePointDao extends GenericDao {
    private static final QGeolattePointEntity qGeolattePoint = QGeolattePointEntity.geolattePointEntity;
    private static final QGeolattePointEntity qGeolattePointAlter = new QGeolattePointEntity("geolatteAlter");

    @Autowired
    private GeolatteBean geolatteBean;

    @Autowired
    private DataSource dataSourceDupa;

    public void loadGisPoints() {
        List<GeolattePointEntity> allNoFetch = findAllNoFetch(qGeolattePoint);
        geolatteBean.setAllPoints(allNoFetch);
    }

    public void saveManyGeolatte() {
//        Connection  connection = new Jdbc4Connection("jdbc:postgres://localhost:5432/baza",);
        SQLTemplates templates = new PostGISTemplates();
        Configuration configuration = new Configuration(templates);
//
//        SQLQuery sqlQuery =new SQLQuery(connection,configuration);
//
//
//

        SQLQueryFactory factory = new SQLQueryFactory(configuration, dataSourceDupa);
        SQLQuery query = factory.query();
        List<GeolattePointEntity> geolattePointEntities = query.list(qGeolattePoint);
//
//        <property name="driverClassName" value="org.postgresql.Driver"/>
//        <property name="url" value="jdbc:postgres://localhost:5432/baza"/>
//        <property name="username" value="postgres"/>
//        <property name="password" value="123"/>


//        SQLInsertClause query = new SQLInsertClause(new java.sql.Connection() {
//        }configuration);
//        new SQLInsertClause()
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Point point = new Point();
        point.x = 2;
        point.y = 3;
        point.z = 4;
        PGgeometry geometry = new PGgeometry(point);
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
