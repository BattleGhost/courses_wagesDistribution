package org.example.model.organization.employees;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Manager extends Employee{
    private List<Employee> workers;
    private long additionalBonus;

    public Manager(int id, String firstName, String middleName, String secondName, Date birthDate,
                   Date hiringDate, long salary, long salaryBonus) {
        this(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus, new LinkedList<>());
    }

    public Manager(int id, String firstName, String middleName, String secondName, Date birthDate,
                   Date hiringDate, long salary, long salaryBonus, List<Employee> workers) {
        super(id, firstName, middleName, secondName, birthDate, hiringDate, salary, salaryBonus);
        this.type = EmployeeType.MANAGER;
        this.workers = workers;
        this.additionalBonus = 0;
    }

    public List<Employee> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Employee> workers) {
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
