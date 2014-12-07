package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import core.point.jts.JtsPointEntity;
import core.utils.GenericDao;
import core.utils.PointToSaveBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzysztof on 2014-12-07.
 */
@Service
public class JtsGeometryDao extends GenericDao {

}
