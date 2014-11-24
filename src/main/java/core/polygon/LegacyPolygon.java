package core.polygon;

import core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-11-24.
 */
@Entity
@Setter
@Getter
public class LegacyPolygon extends BaseEntity {

    private String name;
    private String code;
}
