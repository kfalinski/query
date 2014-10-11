package core.point;

import com.google.common.collect.Lists;
import core.polygon.Polygon;
import core.polygon.PolygonBean;
import core.polygon.PolygonDao;
import core.polygon.PolygonDto;
import core.polyline.Polyline;
import core.polyline.PolylineBean;
import core.polyline.PolylineDao;
import core.polyline.PolylineDto;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.Date;
import java.util.List;

@Component
public class PointsService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PolygonDao polygonDao;

    @Autowired
    private PolylineDao polylineDao;

    @Autowired
    private PolylineDto polylineDto;

    @Autowired
    private PolygonDto polygonDto;

    @Autowired
    private PolylineBean polylineBean;

    @Autowired
    private PolygonBean polygonBean;

    @Autowired
    private PointDto pointDto;

    @Autowired
    private PointDao pointDao;


    public void savePoints(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSave(loadFile(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deletePoints() {
        int deletedCount = entityManager.createQuery("DELETE FROM PointCustom ").executeUpdate();
        System.out.println(deletedCount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteSelectedPoints() {
        List<PointCustom> selectedPoints = pointDto.getSelectedPoints();
        List<Long> ids = Lists.newArrayList();
        for (PointCustom selectedPoint : selectedPoints) {
            ids.add(selectedPoint.());
        }
        for (Long id : ids) {
            PointCustom pointCustom = entityManager.find(PointCustom.class, id);
            entityManager.remove(pointCustom);
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

    private void splitLinesAndSave(List<String> lines) {
        Date start;
        Date end;
        start = new Date();

        List<PointCustom> pointList = Lists.newArrayList();
        for (String line : lines) {
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
            PointCustom pointCustom = new PointCustom(name, code, x, y, z);
            pointList.add(pointCustom);
        }
        end = new Date();
        System.out.println(end.getTime() - start.getTime());
        pointDao.savePoints(pointList);
    }

    public void populatePolygonBean() {
        double length = 0.0;
        List<PointCustom> pointCustomList = pointDto.getSelectedPoints();
        polygonBean.setAllPoints(pointDto.getSelectedPoints());
        Polygon polygon = new Polygon();
        for (int i = 0; i < pointCustomList.size() - 2; i++) {
            length += getLength(pointCustomList.get(i), pointCustomList.get(i + 1));
        }
        double[] xArray = new double[pointCustomList.size()];
        double[] yArray = new double[pointCustomList.size()];
        for (int i = 0; i < pointCustomList.size(); i++) {
            xArray[i] = pointCustomList.get(i).getX();
            yArray[i] = pointCustomList.get(i).getY();
        }
        polygonBean.setArea(getPolygonArea(xArray, yArray, pointCustomList.size()));
        polygonBean.setLength(length);
        polygonDao.savePolygon(polygon);
    }

    public void populatePolylineBean() {
        double length = 0.0;
        List<PointCustom> pointCustomList = pointDto.getSelectedPoints();
        polylineBean.setAllPoints(pointCustomList);
        Polyline polyline = new Polyline();
        for (int i = 0; i < pointCustomList.size() - 2; i++) {
            length += getLength(pointCustomList.get(i), pointCustomList.get(i + 1));
        }

        polylineBean.setLength(length);
        polylineDao.savePolylile(polyline);
    }

    private double getLength(PointCustom p1, PointCustom p2) {
        return Math.sqrt(
                (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
                        (p1.getY() - p2.getY()) * (p1.getY() - p2.getY())
        );
    }

    public void populatepolylineDto() {
        polylineDto.setAllPolylines(polylineDao.loadPolylines());
    }

    public void populatepolygonDto() {
        polygonDto.setAllPolygons(polygonDao.loadPolygons());
    }

    public void populatePointDto() {
        pointDto.setAllPoints(pointDao.loadPoints());
    }

    public List<String> listFilesForFolder(File startFolder) {
        List<String> listedFiles = Lists.newArrayList();
        for (final File fileEntry : startFolder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                listedFiles.add(fileEntry.getAbsolutePath());
            }
        }
        return listedFiles;
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
}
