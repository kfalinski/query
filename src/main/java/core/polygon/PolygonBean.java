package core.polygon;

import core.point.PointCustom;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Krzysztof on 2014-10-02.
 */
@Component
public class PolygonBean implements Serializable {
    private List<PointCustom> allPoints;
    private double area;
    private double length;

    public List<PointCustom> getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(List<PointCustom> allPoints) {
        this.allPoints = allPoints;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
