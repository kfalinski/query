package core.polyline;

import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by Krzysztof on 2014-11-24.
 */
@Entity
@Setter
@Getter
public class LegacyPolyline extends BaseEntity {

    private String name;
    private String code;

}
