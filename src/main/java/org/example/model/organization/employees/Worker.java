package org.example.model.organization.employees;

import java.util.Date;

public class Worker extends Employee {
    public Worker(int id, String firstName, String middleName, String secondName, Date birthDate,
                  Date hiringDate, long salary, long salaryBonus, int officeId, int departmentId) {
        super(id, firstName, middleName, secondName, birthDate, hiringDate, salary,
                salaryBonus, officeId, departmentId);
        this.type = EmployeeType.WORKER;
    }
}
