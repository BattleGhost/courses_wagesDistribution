package org.example.model;

import org.example.model.database.DatabaseConnection;
import org.example.model.database.DatabaseOperations;
import org.example.model.organization.Department;
import org.example.model.organization.Office;
import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.Manager;
import org.example.model.organization.employees.Other;
import org.example.model.organization.employees.Worker;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Model {
    private Office office;
    public static int officeIncrement;
    public static int departmentIncrement;
    public static int employeeIncrement;

    public Model() {
        setIncrements();
    }

    private static void setIncrements() {
        try {
            officeIncrement = getIncrement("SELECT id from office") + 1;
            departmentIncrement = getIncrement("SELECT id from department") + 1;
            employeeIncrement = getIncrement("SELECT id from employee") + 1;
        } catch (SQLException e) {
            // TODO: 13.09.2021
            officeIncrement = 1;
            departmentIncrement = 1;
            employeeIncrement = 1;
        }
    }

    private static int getIncrement(String query) throws SQLException {
        DatabaseConnection dbc = new DatabaseConnection();
        ResultSet rs = DatabaseOperations.createConnectionAndExecute(dbc, query);
        int maximalId = 0;
        while (rs.next()) {
            int nextId = rs.getInt("id");
            if (nextId > maximalId) {
                maximalId = nextId;
            }
        }
        dbc.closeConnection();
        return maximalId;
    }

    public void save() {
        Objects.requireNonNull(office);
        DatabaseConnection dbc = new DatabaseConnection();
        try {
            DatabaseOperations.saveOrganizationInDB(dbc, office);
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: 13.09.2021
        }
    }

    public void load(int officeId) {
        DatabaseConnection dbc = new DatabaseConnection();
        try {
            office = DatabaseOperations.loadOrganizationFromDB(dbc, officeId);
        } catch (SQLException e) {
            // TODO: 15.09.2021  
            e.printStackTrace();
        } catch (IOException e) {
            // TODO: 15.09.2021  
            e.printStackTrace();
        }
        setIncrements();
    }

    public void createOffice(long salaryFund, long defaultBonus, long defaultManagerBonus) {
        office = Organization.createOffice(officeIncrement++, salaryFund, defaultBonus, defaultManagerBonus);
    }

    public void createDepartmentInOffice(long salaryFund, long defaultBonus, long defaultManagerBonus,
                                         String departmentName) {
        Objects.requireNonNull(office);
        Department department = Organization.createDepartment(office.getOfficeId(), departmentIncrement++, salaryFund,
                defaultBonus, defaultManagerBonus, departmentName);
        office.addDepartment(department);
    }

    private void addEmployeeToDepartment(int departmentId, Employee employee) {
        for (Department department : office.getDepartmentSet()) {
            if (department.getDepartmentId() == departmentId) {
                Organization.addEmployee(department, employee);
                Organization.addEmployee(office, employee);
                break;
            }
        }
    }

    public void removeEmployeeFromDepartment(int departmentId, int employeeId) {
        for (Department department : office.getDepartmentSet()) {
            if (department.getDepartmentId() == departmentId) {
                Organization.removeEmployee(department, employeeId);
                Organization.removeEmployee(office, employeeId);
                break;
            }
        }
    }

    public void addWorkerToDepartment(String firstName, String middleName, String secondName, Date birthDate,
                                      Date hiringDate, long salary, long salaryBonus, int departmentId) {
        addEmployeeToDepartment(departmentId, new Worker(employeeIncrement++, firstName, middleName, secondName,
                birthDate, hiringDate, salary, salaryBonus, office.getOfficeId(), departmentId));
    }

    public void addManagerToDepartment(String firstName, String middleName, String secondName, Date birthDate,
                                      Date hiringDate, long salary, long salaryBonus, int departmentId) {
        addEmployeeToDepartment(departmentId, new Manager(employeeIncrement++, firstName, middleName, secondName,
                birthDate, hiringDate, salary, salaryBonus, office.getOfficeId(), departmentId));
    }

    public void addOtherToDepartment(String firstName, String middleName, String secondName, Date birthDate,
                                       Date hiringDate, long salary, long salaryBonus,
                                     int departmentId, String description) {
        addEmployeeToDepartment(departmentId, new Other(employeeIncrement++, firstName, middleName, secondName,
                birthDate, hiringDate, salary, salaryBonus, office.getOfficeId(), departmentId, description));
    }

    public void attachEmployeeToManager(int managerId, int employeeId) {
        Manager manager = null;
        Employee employee = null;
        for (Employee emp : office.getEmployeeSet()) {
            if (emp instanceof Manager && emp.getId() == managerId) {
                manager = (Manager) emp;
            } else if (emp.getId() == employeeId) {
                employee = emp;
            }
        }
        if (manager != null && employee != null){
            Organization.attachEmployeeToManager(manager, employee);
        }

    }

    public void detachEmployeeFromManager(int managerId, int employeeId) {
        if (managerId == employeeId) {
            return;
        }
        Manager manager = null;
        for (Employee emp : office.getEmployeeSet()) {
            if (emp instanceof Manager && emp.getId() == managerId) {
                manager = (Manager) emp;
            }
        }
        if (manager != null) {
            Organization.detachEmployeeFromManager(manager, employeeId);
        }
    }

    public List<Integer> getAllOffices() {
        DatabaseConnection dbc = new DatabaseConnection();
        try {
            return DatabaseOperations.getOfficesIds(dbc);
        } catch (SQLException e) {
            // TODO: 16.09.2021
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Office getOffice() {
        return office;
    }
}
