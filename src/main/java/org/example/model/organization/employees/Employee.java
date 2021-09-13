package org.example.model.organization.employees;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class Employee {
    protected int id;
    protected int officeId;
    protected int departmentId;
    protected String firstName;
    protected String middleName;
    protected String secondName;
    protected Date birthDate;
    protected Date hiringDate;
    protected long salary;
    protected long salaryBonus;
    protected EmployeeType type;

    public Employee(int id, String firstName, String middleName, String secondName, Date birthDate,
                    Date hiringDate, long salary, long salaryBonus, int officeId, int departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.salaryBonus = salaryBonus;
        this.officeId = officeId;
        this.departmentId = departmentId;
    }

    public void calculateSalaryBonus(Date currentDate, long bonusAmount) {
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
                   return new Other(employee.id, employee.firstName, employee.middleName, employee.secondName,
                           employee.birthDate, employee.hiringDate, employee.salary, employee.salaryBonus,
                           employee.officeId, employee.departmentId);
               case MANAGER:
                   return new Manager(employee.id, employee.firstName, employee.middleName, employee.secondName,
                           employee.birthDate, employee.hiringDate, employee.salary, employee.salaryBonus,
                           employee.officeId, employee.departmentId);
               case WORKER:
                   return new Worker(employee.id, employee.firstName, employee.middleName, employee.secondName,
                           employee.birthDate, employee.hiringDate, employee.salary, employee.salaryBonus,
                           employee.officeId, employee.departmentId);
           }
        }
        return employee;
    }

    @Override
    public String toString() {
        return id + " | " + firstName + " " + secondName + " " + middleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getSalaryBonus() {
        return salaryBonus;
    }

    public void setSalaryBonus(long salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
