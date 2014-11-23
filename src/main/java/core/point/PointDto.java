package core.point;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Krzysztof on 2014-10-01.
 */
@Component
public class PointDto implements Serializable {
    private static final Long serialVersionUID = 1241415135L;
    private List<PointCustom> allPoints = Lists.newArrayList();
    private List<PointCustom> selectedPoints = Lists.newArrayList();

    public List<PointCustom> getSelectedPoints() {
        return selectedPoints;
    }

    public void setSelectedPoints(List<PointCustom> selectedPoints) {
        this.selectedPoints = selectedPoints;
    }

    public List<PointCustom> getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(List<PointCustom> allPoints) {
        this.allPoints = allPoints;
    }

}
