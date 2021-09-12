package org.example.model.organization.employees;

import java.util.Calendar;
import java.util.Date;

public abstract class Employee {
    protected int id;
    protected String name;
    protected Date birthDate;
    protected Date hiringDate;
    protected int salary;
    protected int salaryBonus;
    protected EmployeeType type;

    public Employee(int id, String name, Date birthDate, Date hiringDate, int salary, int salaryBonus) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.salaryBonus = salaryBonus;
    }

    public void calculateSalaryBonus(Date currentDate, int bonusAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.setTime(this.birthDate);
        int birthdayMonth = calendar.get(Calendar.MONTH);
        if (currentMonth == birthdayMonth) {
            this.salaryBonus = bonusAmount;
        }
    }

    public static Employee changeType(Employee employee, EmployeeType newType) {
        if (employee.type == newType) {
            return employee;
        } else {
           switch (newType) {
               case OTHER:
                   return new Other(employee.id, employee.name, employee.birthDate,
                           employee.hiringDate, employee.salary, employee.salaryBonus);
               case MANAGER:
                   return new Manager(employee.id, employee.name, employee.birthDate,
                           employee.hiringDate, employee.salary, employee.salaryBonus);
               case WORKER:
                   return new Worker(employee.id, employee.name, employee.birthDate,
                           employee.hiringDate, employee.salary, employee.salaryBonus);
           }
        }
        return employee;
    }
}
