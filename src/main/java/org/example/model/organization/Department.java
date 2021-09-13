package org.example.model.organization;

import org.example.model.organization.employees.Employee;

public class Department extends Office {
    private int departmentId;
    private String departmentName;

    public Department(int officeId, int departmentId, long salaryFund, long defaultBonus,
                      long defaultManagerBonus, String departmentName) {
        super(officeId, salaryFund, defaultBonus, defaultManagerBonus);
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void addEmployee(Employee employee) {
        employee.setDepartmentId(departmentId);
        super.addEmployee(employee);
    }

    @Override
    public String toString() {
        return "("+departmentId+") " + departmentName;
    }
}
