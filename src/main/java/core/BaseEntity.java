package core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Krzysztof on 2014-10-11.
 */
@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
