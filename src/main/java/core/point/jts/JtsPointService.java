package core.point.jts;

import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
import com.vividsolutions.jts.geom.GeometryFactory;
import core.gis.JtsGeometryDao;
import core.gis.JtsGeometryEntity;
import core.point.legacy.LegacyPoint;
import core.utils.GeoService;
import core.utils.PointToSaveBean;
import core.utils.PolygonsView;
import org.geotools.geometry.jts.GeometryBuilder;
import org.geotools.geometry.jts.LiteCoordinateSequenceFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Arrays;
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
    @Autowired
    private PolygonsView polygonsView;
    @Autowired
    private JtsGeometryDao jtsGeometryDao;

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

    @Transactional
    public void saveAsPolygon() {
        List<JtsPointEntity> selectedPoints = jtsPointBean.getSelectedPoints();
        if (selectedPoints == null || selectedPoints.size() == 0) {
            return;
        }
        MapModel polygonModel = polygonsView.getPolygonModel();
        if (polygonModel == null) {
            polygonModel = new DefaultMapModel();
        }
        Polygon polygon = new Polygon();
        for (JtsPointEntity selectedPoint : selectedPoints) {
            polygon.getPaths().add(new LatLng(selectedPoint.getJtsPoint().getX(), selectedPoint.getJtsPoint().getY()));
        }
        JtsGeometryEntity jtsGeometryEntity = new JtsGeometryEntity();
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[selectedPoints.size() + 1];
        int i = 0;
        for (LatLng latLng : polygon.getPaths()) {
            Coordinate coordinate = new Coordinate();
            coordinate.x = latLng.getLat();
            coordinate.y = latLng.getLng();
            coordinate.z = 0;
            coordinates[i++] = (coordinate);
        }
        coordinates[i] = new Coordinate(polygon.getPaths().get(0).getLat(), polygon.getPaths().get(0).getLng(), 0);
        com.vividsolutions.jts.geom.Polygon newPolygon = geometryFactory.createPolygon(coordinates);
        jtsGeometryEntity.setGeometry(newPolygon);
        StringBuilder stringBuilder = new StringBuilder();
        for (JtsPointEntity selectedPoint : selectedPoints) {
            stringBuilder.append(selectedPoint.getName()).append(" ");
        }
        jtsGeometryEntity.setName(stringBuilder.toString());
        polygon.setStrokeColor("#FF9900");
        polygon.setFillColor("#FF9900");
        polygon.setStrokeOpacity(0.7);
        polygon.setFillOpacity(0.7);
        polygonModel.addOverlay(polygon);
        jtsGeometryEntity.setCode(polygon.getId());
        jtsGeometryDao.save(jtsGeometryEntity);
        polygonsView.setPolygonModel(polygonModel);
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
