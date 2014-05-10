package com.github.jntakpe.pp.domain;

import com.github.jntakpe.fmk.domain.GenericDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * Pointage journalier d'un {@link com.github.jntakpe.pp.domain.Employee} sur une {@link com.github.jntakpe.pp.domain.Task}
 *
 * @author jntakpe
 */
@Entity
@SequenceGenerator(name = "SG", sequenceName = "daily_consumption_id_seq")
public class DailyConsumption extends GenericDomain<Long> {

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Task task;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyConsumption that = (DailyConsumption) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DailyConsumption{" +
                "employee=" + employee +
                ", task=" + task +
                ", date=" + date +
                '}';
    }
}
