package core.point;

import core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Krzysztof on 2014-09-09.
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PointCustom extends BaseEntity implements Serializable {

    private String name;
    private String code;
    private double x;
    private double y;
    private double z;

}
