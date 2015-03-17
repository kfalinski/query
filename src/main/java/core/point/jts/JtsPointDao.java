package core.point.jts;

import com.google.common.collect.Lists;
import com.mysema.query.jpa.JPQLQuery;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import core.point.legacy.LegacyPoint;
import core.utils.GenericDao;
import core.utils.GeoService;
import core.utils.PointToSaveBean;
import org.geolatte.geom.Point;
import org.geotools.geometry.jts.GeometryBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class JtsPointDao extends GenericDao {
    private static final QJtsPointEntity qJtsPointEntity = QJtsPointEntity.jtsPointEntity;

    public List<JtsPointEntity> loadJtsPoints() {
        return findAllNoFetch(qJtsPointEntity);
    }

    public void removeAllPoints() {
        removeAllEntities(qJtsPointEntity);
    }

    public void removePoints(List<JtsPointEntity> entities) {
        removeMany(qJtsPointEntity, entities.stream().map(p -> p.getId()).collect(Collectors.toList()));
    }
}

