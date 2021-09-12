package org.example.model.organization.employees;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Manager extends Employee{
    private List<Employee> workers;

    public Manager(int id, String name, Date birthDate, Date hiringDate, int salary, int salaryBonus) {
        super(id, name, birthDate, hiringDate, salary, salaryBonus);
        this.type = EmployeeType.MANAGER;
        this.workers = new LinkedList<>();
    }

    public Manager(int id, String name, Date birthDate, Date hiringDate, int salary, int salaryBonus,
                   List<Employee> workers) {
        this(id, name, birthDate, hiringDate, salary, salaryBonus);
        this.workers = workers;
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

    public void removeWorker(int id) {
        for (Employee employee : this.workers) {
            if (employee.id == id){
                workers.remove(employee);
            }
        }
    }
}
