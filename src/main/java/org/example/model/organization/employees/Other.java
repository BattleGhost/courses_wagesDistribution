package org.example.model.organization.employees;

import java.util.Date;

public class Other extends Employee{
    private String description;

    public Other(int id, String firstName, String middleName, String secondName, Date birthDate,
                 Date hiringDate, long salary, long salaryBonus,
                 int officeId, int departmentId) {
        this(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus,
                officeId, departmentId, "");

    }

    public Other(int id, String firstName, String middleName, String secondName, Date birthDate,
                 Date hiringDate, long salary, long salaryBonus, int officeId, int departmentId, String description) {
        super(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus,
                officeId, departmentId);
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
