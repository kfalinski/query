package core.gis;

import core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.prefs.BackingStoreException;

/**
 * Created by Krzysztof on 2014-12-01.
 */
@Setter
@Getter
@Entity
public class GeometryLatte extends BaseEntity {
    private String name;
    private String code;

    private org.geolatte.geom.Geometry geometryLatte;
}
