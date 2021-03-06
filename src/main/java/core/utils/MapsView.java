package core.utils;

import core.gis.JtsGeometryDao;
import core.gis.JtsGeometryEntity;
import core.point.jts.JtsPointEntity;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Krzysztof on 2014-12-09.
 */
@Component
@Getter
@Setter
public class MapsView {

    @Autowired
    private JtsGeometryDao jtsGeometryDao;

    private String mapCenter;

    private MapModel polygonModel;

    private MapModel polylineModel;

    private MapModel pointModel;

    private Overlay selectedOverlay;

    private String zoom;

    private Marker marker;

    @PostConstruct
    public void init() {
        mapCenter = "50.1017948,13.215401";
        zoom = "4";
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    public void setMapCenter(JtsPointEntity jtsPointEntity) {
        String x = Double.toString(jtsPointEntity.getJtsPoint().getX());
        String y = Double.toString(jtsPointEntity.getJtsPoint().getY());
        this.mapCenter = x.substring(0, x.indexOf(".") + 7) + "," + y.substring(0, y.indexOf(".") + 7);
        this.zoom = "15";
    }

    public void setMapCenter(JtsGeometryEntity jtsGeometryEntity) {
        String x = Double.toString(jtsGeometryEntity.getGeometry().getCentroid().getX());
        String y = Double.toString(jtsGeometryEntity.getGeometry().getCentroid().getY());
        this.mapCenter = x.substring(0, x.indexOf(".") + 7) + "," + y.substring(0, y.indexOf(".") + 7);
        this.zoom = "4";
    }

    @Transactional
    public void deletePolygon() {
        if (selectedOverlay != null) {
            List<Polygon> polygons = polygonModel.getPolygons();
            Iterator iterator = polygons.iterator();
            while (iterator.hasNext()) {
                Polygon polygon = (Polygon)iterator.next();
                if (polygon.getId().equals(selectedOverlay.getId())) {
                    iterator.remove();
                }
            }
            jtsGeometryDao.remove(jtsGeometryDao.findByCode(selectedOverlay.getId()));
            this.selectedOverlay = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Poligon usunięty", null));
        }
    }


    public void onPolygonSelect(OverlaySelectEvent event) {
        this.selectedOverlay = event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Polygon Selected", null));
    }
}