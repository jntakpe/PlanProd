package com.github.jntakpe.pp.domain;

import com.github.jntakpe.fmk.domain.GenericDomain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

/**
 * Bean représentant une tâche
 *
 * @author jntakpe
 */
@Entity
@SequenceGenerator(name = "SG", sequenceName = "task_id_seq")
public class Task extends GenericDomain<Integer> {

    private String name;

    private String description;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "task")
    private Set<DailyConsumption> dailyConsumptions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<DailyConsumption> getDailyConsumptions() {
        return dailyConsumptions;
    }

    public void setDailyConsumptions(Set<DailyConsumption> dailyConsumptions) {
        this.dailyConsumptions = dailyConsumptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (project != null ? !project.equals(task.project) : task.project != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", project=" + project +
                '}';
    }
}
