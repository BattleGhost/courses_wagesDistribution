package org.example.model.database;

import org.example.model.Organization;
import org.example.model.organization.Department;
import org.example.model.organization.Office;
import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.Manager;
import org.example.model.organization.employees.Other;
import org.example.model.organization.employees.Worker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
    public static ResultSet createConnectionAndExecute(DatabaseConnection dbc, String query) throws SQLException {
        dbc.createConnection();
        return execute(dbc, query);
    }

    public static void createConnectionAndUpdate(DatabaseConnection dbc, String query) throws SQLException {
        dbc.createConnection();
        update(dbc, query);
    }

    public static ResultSet execute(DatabaseConnection dbc, String query) throws SQLException {
        Connection connection = dbc.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static void update(DatabaseConnection dbc, String query) throws SQLException {
        System.out.println(query);
        Connection connection = dbc.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public static void saveOrganizationInDB(DatabaseConnection dbc, Office office) throws SQLException {
        String queryTemplate = "INSERT INTO %s(%s) VALUES (%s) as alias ON DUPLICATE KEY UPDATE %s";

        String values = "id, salary_fund, default_bonus, default_manager_bonus";
        String updateValues = getUpdateString(values);
        createConnectionAndUpdate(dbc, String.format(queryTemplate, "office", values,
                "" + office.getOfficeId() + ", " + office.getSalaryFund() +
                        ", " + office.getDefaultBonus() + ", " + office.getDefaultManagerBonus(), updateValues));

        StringBuilder departmentsAppendString = new StringBuilder();
        StringBuilder employeeAppendString = new StringBuilder();
        StringBuilder officeDepartmentsAppendString = new StringBuilder();
        StringBuilder officeEmployeeAppendString = new StringBuilder();
        StringBuilder departmentEmployeeAppendString = new StringBuilder();
        StringBuilder managerEmployeeAppendString = new StringBuilder();

        for (Department department : office.getDepartmentSet()) {

            departmentsAppendString.append("(").append(department.getDepartmentId()).append(", ")
                    .append(department.getSalaryFund()).append(", ")
                    .append(department.getDefaultBonus()).append(", ")
                    .append(department.getDefaultManagerBonus()).append(", ")
                    .append("'").append(department.getDepartmentName()).append("'").append("),");

            officeDepartmentsAppendString.append("(")
                    .append(department.getOfficeId()).append(", ")
                    .append(department.getDepartmentId()).append("),");

            for (Employee employee : department.getEmployeeSet()) {

                employeeAppendString.append("(").append(employee.getId()).append(", ")
                        .append(employee.getOfficeId()).append(", ")
                        .append(employee.getDepartmentId()).append(", ")
                        .append("'").append(employee.getFirstName()).append("'").append(", ")
                        .append("'").append(employee.getMiddleName()).append("'").append(", ")
                        .append("'").append(employee.getSecondName()).append("'").append(", ")
                        .append("'").append(employee.getBirthDate()).append("'").append(", ")
                        .append("'").append(employee.getHiringDate()).append("'").append(", ")
                        .append(employee.getSalary()).append(", ")
                        .append(employee.getSalaryBonus()).append(", ")
                        .append("'").append(employee.getType().name()).append("'").append("),");

                officeEmployeeAppendString.append("(")
                        .append(employee.getOfficeId()).append(", ")
                        .append(employee.getId()).append("),");

                departmentEmployeeAppendString.append("(")
                        .append(employee.getDepartmentId()).append(", ")
                        .append(employee.getId()).append("),");

                if (employee instanceof Manager) {
                    for (Employee emp : ((Manager) employee).getWorkers()) {
                        managerEmployeeAppendString.append("(")
                                .append(employee.getId()).append(", ")
                                .append(emp.getId()).append("),");
                    }
                }
            }
        }
        if (departmentsAppendString.length() > 0) {
            values = "id, salary_fund, default_bonus, default_manager_bonus, department_name";
            updateValues = getUpdateString(values);
            update(dbc, String.format(queryTemplate, "department", values,
                    departmentsAppendString.substring(1, departmentsAppendString.length()-2), updateValues));
        }
        if (employeeAppendString.length() > 0) {
            values = "id, office_id, department_id, first_name, middle_name, second_name, birthday," +
             " hiring_date, salary, salary_bonus, type";
            updateValues = getUpdateString(values);
            update(dbc, String.format(queryTemplate, "employee", values,
                    employeeAppendString.substring(1, employeeAppendString.length()-2), updateValues));
        }
        if (officeDepartmentsAppendString.length() > 0) {
            values = "office_id, department_id";
            updateValues = getUpdateString(values);
            update(dbc, String.format(queryTemplate, "office_departments", values,
                    officeDepartmentsAppendString.substring(1, officeDepartmentsAppendString.length()-2),
                    updateValues));
        }
        if (officeEmployeeAppendString.length() > 0) {
            values = "office_id, employee_id";
            updateValues = getUpdateString(values);
            update(dbc, String.format(queryTemplate, "office_employees", values,
                    officeEmployeeAppendString.substring(1, officeEmployeeAppendString.length()-2), updateValues));
        }
        if (departmentEmployeeAppendString.length() > 0) {
            values = "department_id, employee_id";
            updateValues = getUpdateString(values);
            update(dbc, String.format(queryTemplate, "department_employees", values,
                    departmentEmployeeAppendString.substring(1, departmentEmployeeAppendString.length()-2),
                    updateValues));
        }
        if (managerEmployeeAppendString.length() > 0) {
            values = "manager_id, employee_id";
            updateValues = getUpdateString(values);
            update(dbc, String.format(queryTemplate, "manager_employees", values,
                    managerEmployeeAppendString.substring(1, managerEmployeeAppendString.length()-2), updateValues));
        }

        dbc.closeConnection();
    }

    public static Office loadOrganizationFromDB(DatabaseConnection dbc, int officeId) throws SQLException, IOException{
        String queryTemplate = "SELECT * FROM %s WHERE %s";
        String joinTemplate = "JOIN %s ON (%s)";

        Office office = null;

        String loadOffice = String.format(queryTemplate, "office", "id = " + officeId);

        ResultSet result = createConnectionAndExecute(dbc, loadOffice);

        if (result.next()) {
            office = new Office(result.getInt("id"), result.getLong("salary_fund"),
                    result.getLong("default_bonus"), result.getLong("default_manager_bonus"));

        }

        if (office == null) {
            throw new IOException();
        }

        String loadDepartments = String.format(queryTemplate, "office " +
                String.format(joinTemplate, "office_departments", "office.id = office_departments.office_id")
                + " " + String.format(joinTemplate, "department", "office_departments.department_id = department.id"),
                "office.id = " + officeId);

        result = execute(dbc, loadDepartments);

        while (result.next()) {
            Organization.addDepartmentToOffice(office, new Department(
                    officeId,
                    result.getInt("department.id"),
                    result.getLong("department.salary_fund"),
                    result.getLong("department.default_bonus"),
                    result.getLong("department.default_manager_bonus"),
                    result.getString("department.department_name")
            ));
        }

        String loadEmployees = String.format(queryTemplate, "office " +
                String.format(joinTemplate, "office_departments", "office.id = office_departments.office_id")
                        + " " + String.format(joinTemplate, "department",
                        "office_departments.department_id = department.id")
                        + " " + String.format(joinTemplate, "department_employees",
                        "department.id = department_employees.department_id")
                        + " " + String.format(joinTemplate, "employee",
                        "department_employees.employee_id = employee.id"),
                "office.id = " + officeId);

        result = execute(dbc, loadEmployees);

        while (result.next()) {
            int departmentId = result.getInt("department.id");
            for (Department department : office.getDepartmentSet()) {
                if (department.getDepartmentId() == departmentId) {
                    switch (result.getString("employee.type")) {
                        case "MANAGER":
                            Organization.addEmployee(department, new Manager(
                                    result.getInt("employee.id"),
                                    result.getString("employee.first_name"),
                                    result.getString("employee.middle_name"),
                                    result.getString("employee.second_name"),
                                    result.getDate("employee.birthday"),
                                    result.getDate("employee.hiring_date"),
                                    result.getLong("employee.salary"),
                                    result.getLong("employee.salary_bonus"),
                                    result.getInt("employee.office_id"),
                                    result.getInt("employee.department_id")
                            ));
                            break;
                        case "WORKER":
                            Organization.addEmployee(department, new Worker(
                                    result.getInt("employee.id"),
                                    result.getString("employee.first_name"),
                                    result.getString("employee.middle_name"),
                                    result.getString("employee.second_name"),
                                    result.getDate("employee.birthday"),
                                    result.getDate("employee.hiring_date"),
                                    result.getLong("employee.salary"),
                                    result.getLong("employee.salary_bonus"),
                                    result.getInt("employee.office_id"),
                                    result.getInt("employee.department_id")
                            ));
                            break;
                        case "OTHER":
                            Organization.addEmployee(department, new Other(
                                    result.getInt("employee.id"),
                                    result.getString("employee.first_name"),
                                    result.getString("employee.middle_name"),
                                    result.getString("employee.second_name"),
                                    result.getDate("employee.birthday"),
                                    result.getDate("employee.hiring_date"),
                                    result.getLong("employee.salary"),
                                    result.getLong("employee.salary_bonus"),
                                    result.getInt("employee.office_id"),
                                    result.getInt("employee.department_id"),
                                    result.getString("employee.description")
                            ));
                            break;
                    }
                    break;
                }
            }
        }
        office.synchronizeEmployeeSetFromDepartments();

        String loadManagers = String.format(queryTemplate, "office " +
                        String.format(joinTemplate, "office_employees", "office.id = office_employees.office_id")
                        + " " + String.format(joinTemplate, "employee as manager",
                        "office_employees.employee_id = manager.id")
                        + " " + String.format(joinTemplate, "manager_employees",
                        "manager.id = manager_employees.manager_id")
                        + " " + String.format(joinTemplate, "employee",
                        "manager_employees.employee_id = employee.id"),
                "office.id = " + officeId);

        result = execute(dbc, loadManagers);

        while (result.next()) {
            int managerId = result.getInt("manager.id");
            int employeeId = result.getInt("employee.id");
            Employee manager = null;
            Employee worker = null;
            for (Employee employee : office.getEmployeeSet()) {
                if (employee.getId() == managerId) {
                    manager = employee;
                } else if (employee.getId() == employeeId) {
                    worker = employee;
                }
                if (manager != null && worker != null) {
                    ((Manager) manager).addNewWorker(worker);
                    break;
                }
            }
        }

        dbc.closeConnection();

        return office;
    }

    private static String getUpdateString(String values) {
        StringBuilder updateString = new StringBuilder();
        String[] valuesArray = values.split(", ");
        for (String value : valuesArray) {
            updateString.append(value).append("=alias.").append(value).append(",");
        }
        return updateString.substring(0, updateString.length()-1);
    }
}
