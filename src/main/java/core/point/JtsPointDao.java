package core.point;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.spatial.PostGISTemplates;
import core.utils.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class JtsPointDao extends GenericDao {
    private static final QJtsPointEntity qJtsPointEntity = QJtsPointEntity.jtsPointEntity;
    private static final QJtsPointEntity qJtsPointEntityAlter = QJtsPointEntity.jtsPointEntity;

    @Autowired
    private JtsPointBean jtsPointBean;

    @Autowired
    private DataSource dataSource;

    public void loadGisPoints() {


        SQLTemplates templates = new PostGISTemplates();
        Configuration configuration = new Configuration(templates);
        SQLQueryFactory factory = new SQLQueryFactory(configuration, dataSource);

        SQLQuery query = factory.query();

        List<JtsPointEntity> list = query.from(qJtsPointEntity).list(qJtsPointEntity);
        System.out.println(list);
    }

    public List<JtsPointEntity> loadClose(double meters) {
        JPQLQuery query = buildQuery(qJtsPointEntityAlter);
        query = query.where(qJtsPointEntityAlter.id.eq(2L));
        JtsPointEntity jtsPointEntity = query.singleResult(qJtsPointEntityAlter);

        JPQLQuery query2 = buildQuery(qJtsPointEntity);
        query2 = query2.where(qJtsPointEntity.jtsPoint.distance(jtsPointEntity.getJtsPoint()).lt(meters));
        List<JtsPointEntity> jtsPointEntityList = query2.list(qJtsPointEntity);
        return jtsPointEntityList;
    }
}
