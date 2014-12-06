package core.point.legacy;

import com.google.common.collect.Lists;
import core.point.QLegacyPoint;
import core.utils.GeoService;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Service
public class LegacyPointService {

    @Autowired
    private LegacyPointBean legacyPointBean;

    @Autowired
    private GeoService geoService;

    @Autowired
    private LegacyPointDao legacyPointDao;

    private static final QLegacyPoint Q_LEGACY_POINT = QLegacyPoint.legacyPoint;

    public void loadLegacyPoints() {
        List<LegacyPoint> legacyPointList = legacyPointDao.findAllNoFetch(Q_LEGACY_POINT);
        legacyPointBean.setAllPoints(legacyPointList);
    }

    @Transactional
    public void deleteSelectedPoints() {
        legacyPointDao.deleteSelectedPoints(legacyPointBean.getSelectedPoints());
    }

    public void deleteAllPoints() {
        legacyPointDao.deleteAllPoints();
    }

    public void populatePolygonBean() {
    }

    public void populatePolylineBean() {
    }

    public void saveLegacyPoints(FileUploadEvent event) throws IOException {
        try {
            splitLinesAndSaveLegacyPoints(geoService.loadFile(event.getFile().getInputstream()));
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitLinesAndSaveLegacyPoints(List<String> lines) {
        List<LegacyPoint> pointList = Lists.newArrayList();
        LegacyPoint legacyPoint;
        for (String line : lines) {
            legacyPoint = geoService.splitIternal(line);
            pointList.add(legacyPoint);
        }
        legacyPointDao.saveMany(pointList);
    }

}
