package core.polygon;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
* Created by Krzysztof on 2014-10-04.
*/
@Component
public class PolygonDto implements Serializable {
    private static final Long serialVersionUID = 124544434135L;
    private List<Polygon> allPolygons = Lists.newArrayList();

    public List<Polygon> getAllPolygons() {
        return allPolygons;
    }

    public void setAllPolygons(List<Polygon> allPolygons) {
        this.allPolygons = allPolygons;
    }
}
