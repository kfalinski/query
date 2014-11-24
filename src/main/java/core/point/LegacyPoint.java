package core.point;

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
public class LegacyPoint extends BaseEntity {

    private String name;
    private String code;
    private double x;
    private double y;
    private double z;

    public LegacyPoint() {
    }

    public LegacyPoint(String name, String code, double x, double y, double z) {
        this.name = name;
        this.code = code;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
