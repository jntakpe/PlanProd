package com.github.jntakpe.pp.domain;

import com.github.jntakpe.fmk.domain.GenericDomain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Bean représentant la relation entre un employée et un projet
 *
 * @author jntakpe
 */
@Entity
@SequenceGenerator(name = "SG", sequenceName = "employee_project_id")
public class EmployeeProject extends GenericDomain<Integer> {

    @NotNull
    @ManyToOne
    private Employee employee;

    @NotNull
    @ManyToOne
    private Project project;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Task task;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeProject that = (EmployeeProject) o;

        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (project != null ? !project.equals(that.project) : that.project != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }


}
