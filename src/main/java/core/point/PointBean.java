package core.point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Krzysztof on 2014-10-02.
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointBean implements Serializable{

    @Autowired
    private transient PointDao pointDao;

    private String name;
    private String code;
    private double x;
    private double y;
    private double z;

    private String wktName;
    private String wktCode;
    private String wktValue;


}
