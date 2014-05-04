package com.github.jntakpe.fmk.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe à étendre pour la création d'entités génériques du framework.
 *
 * @author jntakpe
 */
@MappedSuperclass
public abstract class GenericDomain<S extends Number> implements Serializable {

    @Id
    @GeneratedValue(generator = "SG", strategy = GenerationType.SEQUENCE)
    private S id;

    @Version
    private Integer version;

    public S getId() {
        return id;
    }

    public void setId(S id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
