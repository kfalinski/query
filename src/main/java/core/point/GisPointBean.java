package core.point;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Component
@Setter
@Getter
public class GisPointBean {
    private List<GisPoint> selectedPoints;
    private List<GisPoint> allPoints;
}
