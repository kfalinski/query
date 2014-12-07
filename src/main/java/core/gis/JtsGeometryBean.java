package core.gis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzysztof on 2014-12-07.
 */
@Component
@Setter
@Getter
public class JtsGeometryBean {

    private List<JtsGeometryEntity> allJtsGeometry;
    private List<JtsGeometryEntity> selectedJtsGeometries;
}
