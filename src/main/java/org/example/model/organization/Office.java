package org.example.model.organization;

import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.Manager;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Office {
    private long salaryFund;
    private long defaultBonus;
    private long defaultManagerBonus;
    private List<Employee> employeeList;

    public Office(long salaryFund, long defaultBonus, long defaultManagerBonus, List<Employee> employeeList) {
        this.salaryFund = salaryFund;
        this.defaultBonus = defaultBonus;
        this.defaultManagerBonus = defaultManagerBonus;
        this.employeeList = employeeList;
    }

    public Office(long salaryFund, long defaultBonus, long defaultManagerBonus) {
        this(salaryFund, defaultBonus, defaultManagerBonus, new LinkedList<>());
    }

    public long getSalaryFund() {
        return salaryFund;
    }

    public long getDefaultBonus() {
        return defaultBonus;
    }

    public long getDefaultManagerBonus() {
        return defaultManagerBonus;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setSalaryFund(long salaryFund) {
        this.salaryFund = salaryFund;
    }

    public void setDefaultBonus(long defaultBonus) {
        this.defaultBonus = defaultBonus;
    }

    public void setDefaultManagerBonus(long defaultManagerBonus) {
        this.defaultManagerBonus = defaultManagerBonus;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void calculateBonus() {
        for (Employee employee : employeeList) {
            employee.calculateSalaryBonus(new Date(), defaultBonus);
            if (employee instanceof Manager)
                ((Manager) employee).calculateAdditionalBonus(defaultManagerBonus);
        }
    }

    public long calculateTotalCosts() {
        long totalCosts = 0;
        for (Employee employee : employeeList) {
            totalCosts += employee.getSalary();
            totalCosts += employee.getSalaryBonus();
            if (employee instanceof Manager)
                totalCosts += ((Manager) employee).getAdditionalBonus();
        }
        return totalCosts;
    }

    public long calculateStandardBonusAmount() {
        long totalBonus = 0;
        for (Employee employee : employeeList) {
            totalBonus += employee.getSalaryBonus();
        }
        return totalBonus;
    }

    public void recalculateSalary(RecalculationScheme scheme) {
        calculateBonus();
        long totalCosts = calculateTotalCosts();
        long remainder = salaryFund - totalCosts;
        switch (scheme) {
            case UNIFORM:
                long toAdd = remainder / employeeList.size();
                for (Employee employee: employeeList) {
                    employee.setSalary(employee.getSalary() + toAdd);
                }
                break;
            case PROPORTIONAL:
                long totalSum = totalCosts - calculateStandardBonusAmount();
                for (Employee employee: employeeList) {
                    employee.setSalary(employee.getSalary() +
                            (long) (remainder * ((float) employee.getSalary() / totalSum)));
                    if (employee instanceof Manager) {
                        ((Manager) employee).setAdditionalBonus(((Manager) employee).getAdditionalBonus() +
                                (long) (remainder * ((float) ((Manager) employee).getAdditionalBonus() / totalSum)));
                    }
                }
                break;
        }
    }
}
