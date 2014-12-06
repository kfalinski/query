package core.point.jts;

import com.google.common.collect.Lists;
import com.mysema.query.jpa.JPQLQuery;
import core.point.legacy.LegacyPoint;
import core.point.QJtsPointEntity;
import core.utils.GenericDao;
import core.utils.GeoService;
import org.geotools.geometry.jts.GeometryBuilder;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class JtsPointDao extends GenericDao {
    private static final QJtsPointEntity qJtsPointEntity = QJtsPointEntity.jtsPointEntity;
    private static final QJtsPointEntity qJtsPointEntityAlter = QJtsPointEntity.jtsPointEntity;

    @Autowired
    private JtsPointBean jtsPointBean;

    @Autowired
    private GeoService geoService;

    public void loadJtsPoints() {
        List<JtsPointEntity> allNoFetch = findAllNoFetch(qJtsPointEntity);
        jtsPointBean.setAllPoints(allNoFetch);
    }

    public List<JtsPointEntity> loadClose(double meters) {
        JPQLQuery query = buildQuery(qJtsPointEntityAlter);
        query = query.where(qJtsPointEntityAlter.id.eq(2L));
        JtsPointEntity jtsPointEntity = query.singleResult(qJtsPointEntityAlter);

        JPQLQuery query2 = buildQuery(qJtsPointEntity);
        query2 = query2.where(qJtsPointEntity.jtsPoint.distance(jtsPointEntity.getJtsPoint()).lt(meters));
        List<JtsPointEntity> jtsPointEntityList = query2.list(qJtsPointEntity);
        return jtsPointEntityList;
    }

    public void saveGisPoints(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSaveGisPoints(geoService.loadFile(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitLinesAndSaveGisPoints(List<String> lines) {
        List<JtsPointEntity> jtsPointEntityList = Lists.newArrayList();
        LegacyPoint legacyPoint ;
        JtsPointEntity jtsPointEntity;
        GeometryBuilder geometryBuilder = new GeometryBuilder();
        for (String line : lines) {
            legacyPoint = geoService.splitIternal(line);
            double x = legacyPoint.getX();
            double y = legacyPoint.getY();
            double z = legacyPoint.getZ();
            com.vividsolutions.jts.geom.Point point = geometryBuilder.pointZ(x, y, z);
            String name = legacyPoint.getName();
            String code = legacyPoint.getCode();
            jtsPointEntity = new JtsPointEntity(name, code, point, z);
            jtsPointEntityList.add(jtsPointEntity);
        }
        saveMany(jtsPointEntityList);
    }
}
