package org.example.model.organization.employees.comparators;

import org.example.model.organization.employees.Employee;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getSecondName().compareTo(o2.getSecondName());
    }
}
