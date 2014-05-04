package com.github.jntakpe.pp.repository;

import com.github.jntakpe.fmk.repository.GenericRepository;
import com.github.jntakpe.pp.domain.Project;

/**
 * Publication des méthodes de modification de l'entité {@link com.github.jntakpe.pp.domain.Project}
 *
 * @author jntakpe
 */
public interface ProjectRepository extends GenericRepository<Project, Integer> {

    Project findByName(String name);

}
