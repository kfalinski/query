package core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created by Krzysztof on 2014-10-11.
*/
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 6548347367579720578L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String code;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null || other.id == null) {
            return false;
        }
        if (id.equals(other.id)) {
            return true;
        } else {
            return false;
        }
    }
}
