package com.github.jntakpe.pp.domain;

import com.github.jntakpe.fmk.config.Authority;
import com.github.jntakpe.fmk.domain.GenericDomain;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Bean représentant un collaborateur
 *
 * @author jntakpe
 */
@Entity
@SequenceGenerator(name = "SG", sequenceName = "employee_id_seq")
public class Employee extends GenericDomain<Integer> {

    @NotNull
    private String login;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Email
    private String mail;

    @Digits(integer = 5, fraction = 0)
    private String phone;

    @OneToMany(mappedBy = "manager")
    private Set<Project> managedProjects;

    @OneToMany(mappedBy = "employee")
    private Set<DailyConsumption> dailyConsumptions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(Set<Project> managedProjects) {
        this.managedProjects = managedProjects;
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

        Employee employee = (Employee) o;

        return !(login != null ? !login.equals(employee.login) : employee.login != null);

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
