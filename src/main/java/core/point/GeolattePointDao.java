package core.point;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.spatial.PostGISTemplates;
import core.utils.GenericDao;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
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

    public void loadGisPoints() {
        List<GeolattePointEntity> allNoFetch = findAllNoFetch(qGeolattePoint);
        geolatteBean.setAllPoints(allNoFetch);
    }

    public void saveManyGeolatte(Collection<GeolattePointEntity> geolattePointEntities) {
//        Connection  connection ;
        SQLTemplates templates = new PostGISTemplates();
        Configuration configuration = new Configuration(templates);
//        SQLInsertClause query = new SQLInsertClause(new java.sql.Connection() {
//        }configuration);
//        new SQLInsertClause()
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Point point = new Point();
            point.x = 2;
            point.y = 3;
            point.z = 4;
            PGgeometry geometry = new PGgeometry(point);
            mPreparedStatementInsertObservation.setObject(1, geometry);
            Connection mConnection = DriverManager.getConnection("jdbc:postgres://localhost:5432/baza", "postgres", "123");
            ((org.postgresql.PGConnection) mConnection).addDataType("geometry",org.postgis.PGgeometry.class);
            mPreparedStatementInsertObservation = mConnection.prepareStatement(INSERT_OBSERVATION_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<GeolattePointEntity> loadClose(double meters) {
        SQLTemplates templates = new PostGISTemplates();
        Configuration configuration = new Configuration(templates);
        SQLQuery query = new SQLQuery(configuration);
        query = query.where(qGeolattePointAlter.id.eq(2L));
        GeolattePointEntity geolattePointEntity = query.singleResult(qGeolattePointAlter);

        JPQLQuery query2 = buildQuery(qGeolattePoint);
        query2 = query2.where(qGeolattePoint.geolattePoint.distance(geolattePointEntity.getGeolattePoint()).lt(meters));
        List<GeolattePointEntity> geolattePointEntityList = query2.list(qGeolattePoint);
        return geolattePointEntityList;
    }
}
