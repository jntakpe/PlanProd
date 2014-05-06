package com.github.jntakpe.pp.web;

import com.github.jntakpe.pp.domain.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Affichage des pages relatives à l'entité {@link com.github.jntakpe.pp.domain.Project}
 *
 * @author jntakpe
 */
@Controller
@RequestMapping("/project")
public class ListController extends ListController<Project, Integer> {

}
