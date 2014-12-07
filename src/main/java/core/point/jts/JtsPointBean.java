package core.point.jts;

import core.point.jts.JtsPointEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Krzysztof on 2014-11-30.
 */
@Component
@Setter
@Getter
public class JtsPointBean {
    @Autowired
    private JtsPointDao jtsPointDao;

    private List<JtsPointEntity> selectedPoints;
    private List<JtsPointEntity> allPoints;
    private List<JtsPointEntity> returnedPoints;

    @PostConstruct
    public void init() {
        jtsPointDao.loadJtsPoints();
    }
}
