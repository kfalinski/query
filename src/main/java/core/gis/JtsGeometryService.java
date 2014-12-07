package core.gis;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import core.utils.PointToSaveBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzysztof on 2014-12-07.
 */
@Service
public class JtsGeometryService {

    @Autowired
    private PointToSaveBean pointToSaveBean;
    @Autowired
    private JtsGeometryDao jtsGeometryDao;
    @Autowired
    private JtsGeometryBean jtsGeometryBean;

    @Transactional
    public void saveWktToJtsGeometry() {
        String wktValue = pointToSaveBean.getWktValue();
        String wktName = pointToSaveBean.getWktName();
        String wktCode = pointToSaveBean.getWktCode();
        WKTReader fromText = new WKTReader();
        Geometry geom;
        JtsGeometryEntity jtsGeometryEntity;
        try {
            geom = fromText.read(wktValue);
            jtsGeometryEntity = new JtsGeometryEntity(wktName, wktCode, geom);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktValue);
        }
        jtsGeometryDao.save(jtsGeometryEntity);
    }

    public void loadGeometries() {
        jtsGeometryBean.setAllJtsGeometry(jtsGeometryDao.loadGeometries());
    }
}
