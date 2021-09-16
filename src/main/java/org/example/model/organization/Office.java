package org.example.model.organization;

import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.Manager;

import java.util.*;

public class Office {
    protected int officeId;
    protected long salaryFund;
    protected long defaultBonus;
    protected long defaultManagerBonus;
    protected Set<Employee> employeeSet;
    private Set<Department> departmentSet;

    public Office(int officeId, long salaryFund, long defaultBonus, long defaultManagerBonus) {
        this.salaryFund = salaryFund;
        this.defaultBonus = defaultBonus;
        this.defaultManagerBonus = defaultManagerBonus;
        this.officeId = officeId;
        this.employeeSet = new HashSet<>();
        this.departmentSet = new HashSet<>();
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

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
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

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }

    public void calculateBonus() {
        for (Employee employee : employeeSet) {
            employee.calculateSalaryBonus(new Date(), defaultBonus);
            if (employee instanceof Manager)
                ((Manager) employee).calculateAdditionalBonus(defaultManagerBonus);
        }
    }

    public long calculateTotalCosts() {
        long totalCosts = 0;
        for (Employee employee : employeeSet) {
            totalCosts += employee.getSalary();
            totalCosts += employee.getSalaryBonus();
            if (employee instanceof Manager)
                totalCosts += ((Manager) employee).getAdditionalBonus();
        }
        return totalCosts;
    }

    public long calculateStandardBonusAmount() {
        long totalBonus = 0;
        for (Employee employee : employeeSet) {
            totalBonus += employee.getSalaryBonus();
        }
        return totalBonus;
    }

    public void recalculateSalary(RecalculationScheme scheme) {
        calculateBonus();
        long totalCosts = calculateTotalCosts();
        long remainder = salaryFund - totalCosts;
        if (remainder <= 0) {
            return;
        }
        switch (scheme) {
            case UNIFORM:
                long toAdd = remainder / employeeSet.size();
                for (Employee employee: employeeSet) {
                    employee.setSalary(employee.getSalary() + toAdd);
                }
                break;
            case PROPORTIONAL:
                long totalSum = totalCosts - calculateStandardBonusAmount();
                for (Employee employee: employeeSet) {
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

    public void addEmployee(Employee employee) {
        employee.setOfficeId(officeId);
        employeeSet.add(employee);
    }

    public void removeEmployee(int id) {
        for (Employee employee : employeeSet) {
            if (employee.getId() == id) {
                employeeSet.remove(employee);
                break;
            }
        }
    }

    public void addDepartment(Department department) {
        for (Employee employee : department.employeeSet) {
            employee.setOfficeId(officeId);
        }
        department.setOfficeId(officeId);
        departmentSet.add(department);
    }

    public void synchronizeEmployeeSetFromDepartments() {
        employeeSet = new HashSet<>();
        getEmployeesFromDepartments();
    }

    public void getEmployeesFromDepartments() {
        for (Department department : departmentSet) {
            employeeSet.addAll(department.employeeSet);
        }
    }

    @Override
    public String toString() {
        return "Office #" + officeId +
                "\nSalary Fund: " + salaryFund +
                "\nDefault Employee Bonus: " + defaultBonus +
                "\nDefault Manager Bonus: " + defaultManagerBonus +
                "\nDepartments amount: " + departmentSet.size() +
                "\nEmployees amount: " + employeeSet.size();
    }
}
