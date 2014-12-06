package core.point.geoltte;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzysztof on 2014-12-01.
 */
@Component
@Setter
@Getter
public class GeolatteBean {
    private List<GeolattePointEntity> selectedPoints;
    private List<GeolattePointEntity> allPoints;
}
