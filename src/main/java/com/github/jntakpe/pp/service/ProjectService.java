package com.github.jntakpe.pp.service;

import com.github.jntakpe.fmk.service.GenericService;
import com.github.jntakpe.pp.domain.Project;
import com.github.jntakpe.pp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services associés à l'entité {@link com.github.jntakpe.pp.domain.Project}
 *
 * @author jntakpe
 */
@Service
public class ProjectService extends GenericService<Project, Integer> {

    @Autowired
    private ProjectRepository projectRepository;

    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
}
