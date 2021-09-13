package org.example.model.organization.employees;

import java.util.Date;

public class Other extends Employee{
    private String description;

    public Other(int id, String firstName, String middleName, String secondName, Date birthDate,
                 Date hiringDate, long salary, long salaryBonus) {
        this(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus, "");

    }

    public Other(int id, String firstName, String middleName, String secondName, Date birthDate,
                 Date hiringDate, long salary, long salaryBonus, String description) {
        super(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus);
        this.type = EmployeeType.OTHER;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
