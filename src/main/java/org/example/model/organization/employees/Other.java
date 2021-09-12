package org.example.model.organization.employees;

import java.util.Date;

public class Other extends Employee{
    private String description;

    public Other(int id, String name, Date birthDate, Date hiringDate, int salary, int salaryBonus) {
        super(id, name, birthDate, hiringDate, salary, salaryBonus);
        this.type = EmployeeType.OTHER;
    }

    public Other(int id, String name, Date birthDate, Date hiringDate, int salary, int salaryBonus,
                 String description) {
        this(id, name, birthDate, hiringDate, salary, salaryBonus);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
