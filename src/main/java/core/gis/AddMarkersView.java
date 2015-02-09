package core.gis;

/**
 * Created by Krzysztof on 2014-10-15.
 */

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import core.point.jts.JtsPointDao;
import core.point.jts.JtsPointEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Setter
@Getter
@Component
public class AddMarkersView {

    @Autowired
    private JtsPointDao jtsPointDao;


    private MapModel markersModel;

    private String title;

    private double lat;

    private double lng;

    @PostConstruct
    public void init() {
        markersModel = new DefaultMapModel();
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        markersModel.addOverlay(marker);
        Coordinate coordinate = new Coordinate(lat, lng, 0);
        GeometryFactory geometryFactory = new GeometryFactory();
        jtsPointDao.save(new JtsPointEntity(title, "punkt z mapy", geometryFactory.createPoint(coordinate), 0));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
}
