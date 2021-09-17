package org.example.model;

import org.example.model.organization.Department;
import org.example.model.organization.Office;
import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.EmployeeType;
import org.example.model.organization.employees.Manager;
import org.example.model.organization.employees.comparators.ComparatorType;
import org.example.model.organization.employees.comparators.HiringComparator;
import org.example.model.organization.employees.comparators.SurnameComparator;

import java.util.*;

public class Organization {
    public static Office createOffice(int officeId, long salaryFund, long defaultBonus, long defaultManagerBonus) {
        return new Office(officeId, salaryFund, defaultBonus, defaultManagerBonus);
    }

    public static Department createDepartment(int officeId, int departmentId, long salaryFund, long defaultBonus,
                                              long defaultManagerBonus, String departmentName) {
        return new Department(officeId, departmentId, salaryFund, defaultBonus, defaultManagerBonus, departmentName);
    }

    public static void addDepartmentToOffice(Office office, Department department) {
        office.addDepartment(department);
    }

    public static void addEmployee(Office organizationUnit, Employee employee) {
        organizationUnit.addEmployee(employee);
    }

    public static void removeEmployee(Office organizationUnit, int id) {
        organizationUnit.removeEmployee(id);
    }

    public static Set<Employee> getAllEmployees(Office organizationUnit) {
        return organizationUnit.getEmployeeSet();
    }

    public static Set<Employee> getAllEmployees(Office organizationUnit, ComparatorType comparatorType) {
        List<Employee> sortedList = new ArrayList<>(organizationUnit.getEmployeeSet());
        switch (comparatorType) {
            case HIRING:
                sortedList.sort(new HiringComparator());
                break;
            case SURNAME:
                sortedList.sort(new SurnameComparator());
                break;
        }
        return new TreeSet<>(sortedList);
    }

    public static void attachEmployeeToManager(Manager manager, Employee employee) {
        manager.addNewWorker(employee);
    }

    public static void detachEmployeeFromManager(Manager manager, int employeeId) {
        manager.removeWorker(employeeId);
    }

    public static Employee changeType(Employee employee, EmployeeType newType) {
        return Employee.changeType(employee, newType);
    }
}
