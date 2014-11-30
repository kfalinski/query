package core.point;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzysztof on 2014-11-24.
 */
@Component
@Getter
@Setter
public class LegacyPointBean {
    private List<LegacyPoint> selectedPoints;
    private List<LegacyPoint> allPoints;
}
