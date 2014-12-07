package core.point.jts;

import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import core.point.legacy.LegacyPoint;
import core.utils.GeoService;
import core.utils.PointToSaveBean;
import org.geotools.geometry.jts.GeometryBuilder;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Krzysztof on 2014-12-07.
 */
@Service
public class JtsPointService {
    @Autowired
    private JtsPointDao jtsPointDao;
    @Autowired
    private GeoService geoService;
    @Autowired
    private PointToSaveBean pointToSaveBean;
    @Autowired
    private JtsPointBean jtsPointBean;

    public void loadJtsPoints() {
        jtsPointBean.setAllPoints(jtsPointDao.loadJtsPoints());
    }
    public void loadClose(double meters) {
        jtsPointBean.setReturnedPoints(jtsPointDao.loadClose(meters));
    }

    @Transactional
    public void saveJtsPointsFromFile(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSaveGisPoints(geoService.loadFile(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void saveXYZPointToJTS() {
        Coordinate coordinate = new Coordinate(pointToSaveBean.getX(), pointToSaveBean.getY(), pointToSaveBean.getZ());
        GeometryFactory geometryFactory = new GeometryFactory();
        com.vividsolutions.jts.geom.Point point = geometryFactory.createPoint(coordinate);
        JtsPointEntity jtsPointEntity = new JtsPointEntity(pointToSaveBean.getName(), pointToSaveBean.getCode(), point, pointToSaveBean.getZ());
        jtsPointDao.save(jtsPointEntity);
    }

    private void splitLinesAndSaveGisPoints(List<String> lines) {
        List<JtsPointEntity> jtsPointEntityList = Lists.newArrayList();
        LegacyPoint legacyPoint;
        JtsPointEntity jtsPointEntity;
        GeometryBuilder geometryBuilder = new GeometryBuilder();
        for (String line : lines) {
            legacyPoint = geoService.splitIternal(line);
            double x = legacyPoint.getX();
            double y = legacyPoint.getY();
            double z = legacyPoint.getZ();
            com.vividsolutions.jts.geom.Point point = geometryBuilder.pointZ(x, y, z);
            String name = legacyPoint.getName();
            String code = legacyPoint.getCode();
            jtsPointEntity = new JtsPointEntity(name, code, point, z);
            jtsPointEntityList.add(jtsPointEntity);
        }
        jtsPointDao.saveMany(jtsPointEntityList);
    }
}
