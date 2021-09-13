package org.example.model.organization.employees;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Manager extends Employee{
    private Set<Employee> workers;
    private long additionalBonus;

    public Manager(int id, String firstName, String middleName, String secondName, Date birthDate,
                   Date hiringDate, long salary, long salaryBonus, int officeId, int departmentId) {
        this(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus,
                officeId, departmentId, new HashSet<>());
    }

    public Manager(int id, String firstName, String middleName, String secondName, Date birthDate,
                   Date hiringDate, long salary, long salaryBonus, int officeId, int departmentId, Set<Employee> workers) {
        super(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus,
                officeId, departmentId);
        this.type = EmployeeType.MANAGER;
        this.workers = workers;
        this.additionalBonus = 0;
    }

    public Set<Employee> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Employee> workers) {
        this.workers = workers;
    }

    public void addNewWorker(Employee employee) {
        this.workers.add(employee);
    }

    public void removeWorker(long id) {
        for (Employee employee : this.workers) {
            if (employee.id == id){
                workers.remove(employee);
                break;
            }
        }
    }

    public void calculateAdditionalBonus(long bonusAmount) {
        additionalBonus = workers.size() * bonusAmount;
    }

    public long getAdditionalBonus() {
        return additionalBonus;
    }

    public void setAdditionalBonus(long additionalBonus) {
        this.additionalBonus = additionalBonus;
    }
}
