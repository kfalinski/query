package core.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Krzysztof on 2014-12-06.
 */
@Component
@Getter
@Setter
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PointToSaveBean {

    private String name;
    private String code;
    private double x;
    private double y;
    private double z;

    private String wktName;
    private String wktCode;
    private String wktValue;
}
