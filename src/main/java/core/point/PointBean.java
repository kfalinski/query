package core.point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Krzysztof on 2014-10-02.
 */
@Component
public class PointBean implements Serializable{

    @Autowired
    private transient PointDao pointDao;

    private String name;
    private String code;
    private double x;
    private double y;
    private double z;

    public void savePoint() {
        PointCustom pointCustom = new PointCustom();
        pointCustom.setName(name);
        pointCustom.setCode(code);
        pointCustom.setX(x);
        pointCustom.setY(y);
        pointCustom.setZ(z);
        pointDao.savePoint(pointCustom);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
