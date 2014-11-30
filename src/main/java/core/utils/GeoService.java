package core.utils;

import com.google.common.collect.Lists;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import core.point.*;
import core.polygon.PolygonDao;
import core.polyline.PolylineDao;
import org.geolatte.geom.builder.internal.GeometryBuilder3D;
import org.geotools.geometry.jts.GeometryBuilder;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.List;

@Service
public class GeoService {

    @Autowired
    private LegacyPointBean legacyPointBean;

    @Autowired
    private LegacyPointDao legacyPointDao;

    @Autowired
    private GisPointDao gisPointDao;

    @Autowired
    private PolygonDao polygonDao;

    @Autowired
    private PolylineDao polylineDao;

    public void saveLegacyPoints(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSaveLegacyPoints(loadFile(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGisPoints(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSaveGisPoints(loadFile(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> loadFile(InputStream event) throws IOException {
        List<String> lines = Lists.newArrayList();
        InputStreamReader ipsr = new InputStreamReader(event);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    private void splitLinesAndSaveLegacyPoints(List<String> lines) {
        List<LegacyPoint> pointList = Lists.newArrayList();
        LegacyPoint legacyPoint;
        for (String line : lines) {
            legacyPoint = splitIternal(line);
            pointList.add(legacyPoint);
        }
        legacyPointDao.saveMany(pointList);
    }

    private void splitLinesAndSaveGisPoints(List<String> lines) {
        List<GisPoint> pointList = Lists.newArrayList();
        LegacyPoint legacyPoint;
        GeometryBuilder geometryBuilder = new GeometryBuilder();
        WKTWriter wktWriter = new WKTWriter();
        WKTReader wktReader =new WKTReader();
        for (String line : lines) {
            legacyPoint = splitIternal(line);
            Point point = geometryBuilder.pointZ(legacyPoint.getX(), legacyPoint.getY(), legacyPoint.getZ());
            GisPoint gisPoint = new GisPoint(legacyPoint.getName(), legacyPoint.getCode(), point);
            pointList.add(gisPoint);
        }
        gisPointDao.saveMany(pointList);
    }

    private LegacyPoint splitIternal(String line) {
        String[] splitted = line.split(" ");
        List<String> splittedList = Lists.newArrayList();
        for (String s : splitted) {
            if (!s.contentEquals(""))
                splittedList.add(s);
        }
        if (splittedList.size() < 5) {
            while (splittedList.size() < 5) {
                splittedList.add("0");
            }
        }
        String name = splittedList.get(0);
        String code = splittedList.get(1);
        double x = Double.valueOf(splittedList.get(2));
        double y = Double.valueOf(splittedList.get(3));
        double z = Double.valueOf(splittedList.get(4));
        return new LegacyPoint(name, code, x, y, z);
    }

//    public void populatePolygonBean() {
//        double length = 0.0;
//        List<PointCustom> pointCustomList = pointDto.getSelectedPoints();
//        polygonBean.setAllPoints(pointDto.getSelectedPoints());
//        Polygon polygon = new Polygon();
//        for (int i = 0; i < pointCustomList.size() - 2; i++) {
//            length += getLength(pointCustomList.get(i), pointCustomList.get(i + 1));
//        }
//        double[] xArray = new double[pointCustomList.size()];
//        double[] yArray = new double[pointCustomList.size()];
//        for (int i = 0; i < pointCustomList.size(); i++) {
//            xArray[i] = pointCustomList.get(i).getX();
//            yArray[i] = pointCustomList.get(i).getY();
//        }
//        polygonBean.setArea(getPolygonArea(xArray, yArray, pointCustomList.size()));
//        polygonBean.setLength(length);
//        polygonDao.savePolygon(polygon);
//    }
//
//    public void populatePolylineBean() {
//        double length = 0.0;
//        List<PointCustom> pointCustomList = pointDto.getSelectedPoints();
//        polylineBean.setAllPoints(pointCustomList);
//        Polyline polyline = new Polyline();
//        for (int i = 0; i < pointCustomList.size() - 2; i++) {
//            length += getLength(pointCustomList.get(i), pointCustomList.get(i + 1));
//        }
//
//        polylineBean.setLength(length);
//        polylineDao.savePolylile(polyline);
//    }
//
//    private double getLength(PointCustom p1, PointCustom p2) {
//        return Math.sqrt(
//                (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
//                        (p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
//        );
//    }
//
//    public void populatepolylineDto() {
//        polylineDto.setAllPolylines(polylineDao.loadPolylines());
//    }
//
//    public void populatepolygonDto() {
//        polygonDto.setAllPolygons(polygonDao.loadPolygons());
//    }
//
//    public void populatePointDto() {
//        pointDto.setAllPoints(pointDaoImpl.loadPoints());
//    }
//
//    private double getPolygonArea(double[] x, double[] y, int count) {
//        double sum_but_no_result = 0;
//
//        for (int i = 0; i < (count - 1); i++)      // count is point number of polygon
//        {
//            sum_but_no_result += x[i] * y[i + 1] + y[i] * x[i + 1];
//        }
//        sum_but_no_result += x[count - 1] * y[0] + y[count - 1] * x[0];
//
//        return Math.abs(sum_but_no_result) / 2.0f;
//    }
}

