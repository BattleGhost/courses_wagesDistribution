package org.example.model.organization.employees.comparators;

import org.example.model.organization.employees.Employee;

import java.util.Comparator;

public class HiringComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getHiringDate().compareTo(o2.getHiringDate());
    }
}
