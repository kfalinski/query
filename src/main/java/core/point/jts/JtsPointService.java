package core.point.jts;

import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import core.gis.AddMarkersView;
import core.gis.JtsGeometryDao;
import core.gis.JtsGeometryEntity;
import core.point.legacy.LegacyPoint;
import core.utils.GeoService;
import core.utils.MapsView;
import core.utils.PointToSaveBean;
import org.geotools.geometry.jts.GeometryBuilder;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.map.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    private MapsView mapsView;

    @Autowired
    private AddMarkersView addMarkersView;

    @Autowired
    private JtsGeometryDao jtsGeometryDao;

    public void loadJtsPoints() {
        List<JtsPointEntity> allPoints = jtsPointDao.loadJtsPoints();
        jtsPointBean.setAllPoints(allPoints);
        populatePointModel(allPoints);
    }

    @Transactional
    public void deleteAllPoints() {
        jtsPointDao.removeAllPoints();
    }

    @Transactional
    public void deleteSelectedPoints() {
        jtsPointDao.removePoints(jtsPointBean.getSelectedPoints());
        List<JtsPointEntity> allPoints = jtsPointBean.getAllPoints();
        allPoints.removeAll(jtsPointBean.getSelectedPoints());
        jtsPointBean.setAllPoints(allPoints);
        addMarkersView.setMarkersModel(new DefaultMapModel());
        populatePointModel(jtsPointBean.getAllPoints());
    }


    private void populatePointModel(List<JtsPointEntity> allPoints) {
        MapModel pointModel = addMarkersView.getMarkersModel();
        for (JtsPointEntity point : allPoints) {
            LatLng latLng = new LatLng(point.getJtsPoint().getX(), point.getJtsPoint().getY());
            Marker marker = new Marker(latLng);
            pointModel.addOverlay(marker);
        }
        addMarkersView.setMarkersModel(pointModel);
    }

    @Transactional
    public void saveJtsPointsFromFile(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSaveGisPoints(geoService.loadFile(event.getFile().getInputstream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void saveXYZPointToJTS() {
        Coordinate coordinate = new Coordinate(pointToSaveBean.getX(), pointToSaveBean.getY(),
                pointToSaveBean.getZ());
        GeometryFactory geometryFactory = new GeometryFactory();
        com.vividsolutions.jts.geom.Point point = geometryFactory.createPoint(coordinate);
        JtsPointEntity jtsPointEntity = new JtsPointEntity(pointToSaveBean.getName(),
                pointToSaveBean.getCode(), point, pointToSaveBean.getZ());
        jtsPointDao.save(jtsPointEntity);
    }

    @Transactional
    public void saveAsPolygon() {
        List<JtsPointEntity> selectedPoints = jtsPointBean.getSelectedPoints();
        if (selectedPoints == null || selectedPoints.size() == 0) {
            return;
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
        jtsGeometryEntity.setCode("poligon stworzony");
        double[] xArray = new double[selectedPoints.size()];
        double[] yArray = new double[selectedPoints.size()];
        for (int j = 0; i < selectedPoints.size(); i++) {
            xArray[j] = selectedPoints.get(i).getJtsPoint().getX();
            yArray[j] = selectedPoints.get(i).getJtsPoint().getY();
        }
        jtsGeometryEntity.setArea(getPolygonArea(xArray, yArray, selectedPoints.size()));
        jtsGeometryDao.save(jtsGeometryEntity);
        populatePolygonModel(polygon);
    }

    private double getPolygonArea(double[] x, double[] y, int count) {
        double sum_but_no_result = 0;

        for (int i = 0; i < (count - 1); i++)      // count is point number of polygon
        {
            sum_but_no_result += x[i] * y[i + 1] + y[i] * x[i + 1];
        }
        sum_but_no_result += x[count - 1] * y[0] + y[count - 1] * x[0];

        return Math.abs(sum_but_no_result) / 2.0f;
    }

    private void populatePolygonModel(Polygon polygon) {
        MapModel polygonModel = mapsView.getPolygonModel();
        if (polygonModel == null) {
            polygonModel = new DefaultMapModel();
        }
        polygon.setStrokeColor("#FF0000");
        polygon.setFillColor("#FF9900");
        polygon.setStrokeOpacity(0.7);
        polygon.setFillOpacity(0.7);
        polygonModel.addOverlay(polygon);
        mapsView.setPolygonModel(polygonModel);
    }

    @Transactional
    public void saveAsPolyline() {
        List<JtsPointEntity> selectedPoints = jtsPointBean.getSelectedPoints();
        if (selectedPoints == null || selectedPoints.size() == 0) {
            return;
        }
        Polyline polyline = new Polyline();
        for (JtsPointEntity selectedPoint : selectedPoints) {
            polyline.getPaths().add(new LatLng(selectedPoint.getJtsPoint().getX(), selectedPoint.getJtsPoint().getY()));
        }
        JtsGeometryEntity jtsGeometryEntity = new JtsGeometryEntity();
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[selectedPoints.size() + 1];
        int i = 0;
        for (LatLng latLng : polyline.getPaths()) {
            Coordinate coordinate = new Coordinate();
            coordinate.x = latLng.getLat();
            coordinate.y = latLng.getLng();
            coordinate.z = 0;
            coordinates[i++] = (coordinate);
        }
        coordinates[i] = new Coordinate(polyline.getPaths().get(0).getLat(), polyline.getPaths().get(0).getLng(), 0);
        com.vividsolutions.jts.geom.MultiPoint newPolyline = geometryFactory.createMultiPoint(coordinates);
        jtsGeometryEntity.setGeometry(newPolyline);
        StringBuilder stringBuilder = new StringBuilder();
        for (JtsPointEntity selectedPoint : selectedPoints) {
            stringBuilder.append(selectedPoint.getName()).append(" ");
        }
        jtsGeometryEntity.setName(stringBuilder.toString());
        jtsGeometryEntity.setCode("stworzona polilinia");
        double[] xArray = new double[selectedPoints.size()];
        double[] yArray = new double[selectedPoints.size()];
        for (int j = 0; i < selectedPoints.size(); i++) {
            xArray[j] = selectedPoints.get(i).getJtsPoint().getX();
            yArray[j] = selectedPoints.get(i).getJtsPoint().getY();
        }
        jtsGeometryEntity.setArea(getPolygonArea(xArray, yArray, selectedPoints.size()));
        jtsGeometryDao.save(jtsGeometryEntity);
        populatePolylineModel(polyline, jtsGeometryEntity);
    }

    private void populatePolylineModel(Polyline polyline, JtsGeometryEntity jtsGeometryEntity) {
        MapModel polylineModel = mapsView.getPolylineModel();
        if (polylineModel == null) {
            polylineModel = new DefaultMapModel();
        }
        polyline.setStrokeColor("#FF0000");
        polyline.setStrokeOpacity(0.7);
        polylineModel.addOverlay(polyline);
        jtsGeometryEntity.setCode(polyline.getId());
        mapsView.setPolylineModel(polylineModel);
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
