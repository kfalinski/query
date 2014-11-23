package core.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof on 2014-09-09.
 */
@Service
public interface PointRepository extends JpaRepository<PointCustom, Integer> {
}
