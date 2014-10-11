package core.polyline;

import core.point.PointCustom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
* Created by Krzysztof on 2014-10-02.
*/
@Entity
public class Polyline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Double length;

    @ManyToMany
    private List<PointCustom> points ;

    public Polyline() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PointCustom> getPoints() {
        return points;
    }

    public void setPoints(List<PointCustom> points) {
        this.points = points;
    }

    public Polyline(List<PointCustom> points) {
        this.points = points;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
