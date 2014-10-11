package core.polyline;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
* Created by Krzysztof on 2014-10-04.
*/
@Component
public class PolylineDto implements Serializable {
    private static final Long serialVersionUID = 124544135L;
    private List<Polyline> allPolylines = Lists.newArrayList();

    public List<Polyline> getAllPolylines() {
        return allPolylines;
    }

    public void setAllPolylines(List<Polyline> allPolylines) {
        this.allPolylines = allPolylines;
    }
}
