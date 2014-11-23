package core.polygon;

import core.point.PointCustom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
* Created by Krzysztof on 2014-10-01.
*/
@Entity
public class Polygon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double length;
    private Double area;
    private String name;

    @ElementCollection(targetClass = PointCustom.class)
    private List<PointCustom> points;

    public Polygon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PointCustom> getPoints() {
        return points;
    }

    public void setPoints(List<PointCustom> points) {
        this.points = points;
    }

}
