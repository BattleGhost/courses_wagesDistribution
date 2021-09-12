package org.example.model.organization.employees;

import java.util.Date;

public class Worker extends Employee {
    public Worker(int id, String name, Date birthDate, Date hiringDate, int salary, int salaryBonus) {
        super(id, name, birthDate, hiringDate, salary, salaryBonus);
        this.type = EmployeeType.WORKER;
    }
}
