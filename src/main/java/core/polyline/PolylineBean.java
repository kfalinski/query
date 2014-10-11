package core.polyline;

import core.point.PointCustom;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
* Created by Krzysztof on 2014-10-04.
*/
@Component
public class PolylineBean implements Serializable {
    private List<PointCustom> allPoints;
    private double length;

    public List<PointCustom> getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(List<PointCustom> allPoints) {
        this.allPoints = allPoints;
    }


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
