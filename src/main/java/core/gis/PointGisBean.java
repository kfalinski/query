package core.gis;

import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzysztof on 2014-10-12.
 */
@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointGisBean {

    private List<PointGis> pointVividList;
//    private org.postgis.Point pointGis;



    public void costam(){

    }

}
