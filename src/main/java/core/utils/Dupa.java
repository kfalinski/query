package core.utils;

/**
 * Created by Krzysztof on 2014-11-30.
 */
public class Dupa {
//    package core.point;
//
//    import com.google.common.collect.Lists;
//    import com.mysema.query.jpa.impl.JPADeleteClause;
//    import com.vividsolutions.jts.geom.Coordinate;
//    import com.vividsolutions.jts.geom.Geometry;
//    import com.vividsolutions.jts.geom.GeometryFactory;
//    import com.vividsolutions.jts.io.ParseException;
//    import com.vividsolutions.jts.io.WKTReader;
//    import core.gis.GeometryGis;
//    import core.gis.GeometryGisDao;
//    import core.gis.PointGisBean;
//    import core.polygon.Polygon;
//    import core.polygon.PolygonBean;
//    import core.polygon.PolygonDao;
//    import core.polygon.PolygonDto;
//    import core.polyline.Polyline;
//    import core.polyline.PolylineBean;
//    import core.polyline.PolylineDao;
//    import core.polyline.PolylineDto;
//    import org.apache.commons.math3.stat.regression.SimpleRegression;
//    import org.geotools.data.FileDataStore;
//    import org.geotools.data.FileDataStoreFinder;
//    import org.geotools.data.shapefile.ShapefileDataStoreFactory;
//    import org.geotools.data.simple.SimpleFeatureSource;
//    import org.geotools.map.FeatureLayer;
//    import org.geotools.map.Layer;
//    import org.geotools.map.MapContent;
//    import org.geotools.styling.SLD;
//    import org.geotools.styling.Style;
//    import org.geotools.swing.JMapFrame;
//    import org.geotools.swing.data.JFileDataStoreChooser;
//    import org.postgis.Point;
//    import org.primefaces.event.FileUploadEvent;
//    import org.primefaces.model.UploadedFile;
//    import org.springframework.beans.factory.annotation.Autowired;
//    import org.springframework.stereotype.Component;
//    import org.springframework.transaction.annotation.Propagation;
//    import org.springframework.transaction.annotation.Transactional;
//
//    import javax.faces.application.FacesMessage;
//    import javax.faces.context.FacesContext;
//    import javax.persistence.EntityManager;
//    import javax.persistence.PersistenceContext;
//    import java.io.*;
//    import java.util.Date;
//    import java.util.List;
//
//    @Component
//    public class PointsService implements Serializable {
//
//        @PersistenceContext
//        private EntityManager entityManager;
//
//        @Autowired
//        private PolygonDao polygonDao;
//
//        @Autowired
//        private PointBean pointBean;
//
//        @Autowired
//        private PolylineDao polylineDao;
//
//        @Autowired
//        private PointGisBean pointGisBean;
//
//        @Autowired
//        private PolylineDto polylineDto;
//
//        @Autowired
//        private PolygonDto polygonDto;
//
//        @Autowired
//        private PolylineBean polylineBean;
//
//        @Autowired
//        private GeometryGisDao geometryGisDao;
//
//        @Autowired
//        private PolygonBean polygonBean;
//
//        @Autowired
//        private PointDto pointDto;
//
//        @Autowired
//        private PointDao pointDao;
//
//        @Transactional(propagation = Propagation.REQUIRED)
//        public void uploadCSV(FileUploadEvent event){
//            UploadedFile file = event.getFile();
//        }
//
//        @Transactional(propagation = Propagation.REQUIRED)
//        public void savePoints(FileUploadEvent event) throws IOException {
//            try {
//                splitLinesAndSave(loadFile(event.getFile().getInputstream()));
//                FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        @Transactional(propagation = Propagation.REQUIRES_NEW)
//        public void deletePoints() {
//            QPointCustom pointCustom = QPointCustom.pointCustom;
//            new JPADeleteClause(entityManager, pointCustom).execute();
//            //        int deletedCount = entityManager.createQuery("DELETE FROM PointCustom ");
////        System.out.println(deletedCount);
//
//        }
//
//
//        @Transactional(propagation = Propagation.REQUIRES_NEW)
//        public void deleteSelectedPoints() {
//            List<PointCustom> selectedPoints = pointDto.getSelectedPoints();
//            QPointCustom pointCustom = QPointCustom.pointCustom;
//            List<Long> ids = Lists.newArrayList();
//            for (PointCustom selectedPoint : selectedPoints) {
//                ids.add(selectedPoint.getId());
//            }
//            new JPADeleteClause(entityManager, pointCustom).where(pointCustom.id.in(ids)).execute();
//        }
//
//        public List<String> loadFile(InputStream event) throws IOException {
//            List<String> lines = Lists.newArrayList();
//            InputStreamReader ipsr = new InputStreamReader(event);
//            BufferedReader br = new BufferedReader(ipsr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                lines.add(line);
//            }
//            br.close();
//            return lines;
//        }
//
//        public void populatePointGIS() {
//            pointGisBean.setPointVividList(geometryGisDao.loadPointGises());
//        }
//
//        public void showMap() throws Exception {
//            // display a data store file chooser dialog for shapefiles
//            File file = JFileDataStoreChooser.showOpenFile("shp", null);
//            if (file == null) {
//                return;
//            }
//
//            FileDataStore store = FileDataStoreFinder.getDataStore(file);
//            SimpleFeatureSource featureSource = store.getFeatureSource();
//
//            // Create a map content and add our shapefile to it
//            MapContent map = new MapContent();
//            map.setTitle("Quickstart");
//
//            Style style = SLD.createSimpleStyle(featureSource.getSchema());
//            Layer layer = new FeatureLayer(featureSource, style);
//            map.addLayer(layer);
//
//            // Now display the map
//            JMapFrame.showMap(map);
//        }
//
//
//        public void saveWktToGeometry() {
//            String wktPoint = pointBean.getWktValue();
//            String wktName = pointBean.getWktName();
//            String wktCode = pointBean.getWktCode();
//            WKTReader fromText = new WKTReader();
//            Geometry geom;
//            GeometryGis geometryGis;
//            try {
//                geom = fromText.read(wktPoint);
//                geometryGis = new GeometryGis(wktName, wktCode, geom);
//            } catch (ParseException e) {
//                throw new RuntimeException("Not a WKT string:" + wktPoint);
//            }
//            geometryGisDao.saveGeometryGIS(geometryGis);
//        }
//
//        public GeometryGis saveXYZPointToGeometry(String name, String code, double x, double y, double z) {
//            Coordinate coordinate = new Coordinate(x, y, z);
//            GeometryFactory geometryFactory = new GeometryFactory();
//            com.vividsolutions.jts.geom.Point point = geometryFactory.createPoint(coordinate);
//            return new GeometryGis(name, code, point);
//        }
//
//        private void splitLinesAndSave(List<String> lines) {
//            Date start;
//            Date end;
//            start = new Date();
//
//            List<Point> pointList = Lists.newArrayList();
//            List<PointCustom> pointCustomList = Lists.newArrayList();
//            List<GeometryGis> geometryGises = Lists.newArrayList();
//            for (String line : lines) {
//                String[] splitted = line.split(" ");
//                List<String> splittedList = Lists.newArrayList();
//                for (String s : splitted) {
//                    if (!s.contentEquals("")) {
//                        splittedList.add(s);
//                    }
//                }
//                if (splittedList.size() < 5) {
//                    while (splittedList.size() < 5) {
//                        splittedList.add("0");
//                    }
//                }
//                String name = splittedList.get(0);
//                String code = splittedList.get(1);
//                double x = Double.valueOf(splittedList.get(2));
//                double y = Double.valueOf(splittedList.get(3));
//                double z = Double.valueOf(splittedList.get(4));
//                Point pointGis = new Point(x, y, z);
//                pointList.add(pointGis);
//                PointCustom pointCustom = new PointCustom(name, code, x, y, z);
//                pointCustomList.add(pointCustom);
//                geometryGises.add(saveXYZPointToGeometry(pointCustom.getName(), pointCustom.getCode(), pointCustom.getX(), pointCustom.getY(), pointCustom.getZ()));
//
//            }
//            pointDao.savePoints(pointCustomList);
//            geometryGisDao.saveGeometryGIS(geometryGises);
//            end = new Date();
//            System.out.println(end.getTime() - start.getTime());
//
//        }
//
//
//        public void populatePolygonBean() {
//            double length = 0.0;
//            List<PointCustom> pointCustomList = pointDto.getSelectedPoints();
//            polygonBean.setAllPoints(pointDto.getSelectedPoints());
//            Polygon polygon = new Polygon();
//            for (int i = 0; i < pointCustomList.size() - 2; i++) {
//                length += getLength(pointCustomList.get(i), pointCustomList.get(i + 1));
//            }
//            double[] xArray = new double[pointCustomList.size()];
//            double[] yArray = new double[pointCustomList.size()];
//            for (int i = 0; i < pointCustomList.size(); i++) {
//                xArray[i] = pointCustomList.get(i).getX();
//                yArray[i] = pointCustomList.get(i).getY();
//            }
//            polygonBean.setArea(getPolygonArea(xArray, yArray, pointCustomList.size()));
//            polygonBean.setLength(length);
//            polygonDao.savePolygon(polygon);
//        }
//
//
//
//        public void populatePolylineBean() {
//            double length = 0.0;
//            List<PointCustom> pointCustomList = pointDto.getSelectedPoints();
//            polylineBean.setAllPoints(pointCustomList);
//            Polyline polyline = new Polyline();
//            for (int i = 0; i < pointCustomList.size() - 2; i++) {
//                length += getLength(pointCustomList.get(i), pointCustomList.get(i + 1));
//            }
//
//            polylineBean.setLength(length);
//            polylineDao.savePolylile(polyline);
//        }
//
//        private double getLength(PointCustom p1, PointCustom p2) {
//            return Math.sqrt(
//                    (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
//                            (p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
//            );
//        }
//
//        public void populatepolylineDto() {
//            polylineDto.setAllPolylines(polylineDao.loadPolylines());
//        }
//
//        public void populatepolygonDto() {
//            polygonDto.setAllPolygons(polygonDao.loadPolygons());
//        }
//
//        public void populatePointDto() {
//            pointDto.setAllPoints(pointDao.loadPoints());
//        }
//
//        public List<String> listFilesForFolder(File startFolder) {
//            List<String> listedFiles = Lists.newArrayList();
//            for (final File fileEntry : startFolder.listFiles()) {
//                if (fileEntry.isDirectory()) {
//                    listFilesForFolder(fileEntry);
//                } else {
//                    listedFiles.add(fileEntry.getAbsolutePath());
//                }
//            }
//            return listedFiles;
//        }
//
//        private double getPolygonArea(double[] x, double[] y, int count) {
//            double sum_but_no_result = 0;
//
//            for (int i = 0; i < (count - 1); i++)      // count is point number of polygon
//            {
//                sum_but_no_result += x[i] * y[i + 1] - y[i] * x[i + 1];
//            }
//            sum_but_no_result += x[count - 1] * y[0] - y[count - 1] * x[0];
//
//            return Math.abs(sum_but_no_result) / 2.0f;
//        }
//    }

}
