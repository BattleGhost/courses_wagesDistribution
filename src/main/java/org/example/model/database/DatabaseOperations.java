package org.example.model.database;

import org.example.model.organization.Department;
import org.example.model.organization.Office;
import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.Manager;

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
        Connection connection = dbc.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public static void saveOrganizationInDB(DatabaseConnection dbc, Office office) throws SQLException {
        String query_template = "INSERT INTO %s(%s) VALUES (%s)";
        createConnectionAndUpdate(dbc, String.format(query_template, "office",
                "id, salary_fund, default_bonus, default_manager_bonus",
                "" + office.getOfficeId() + ", " + office.getSalaryFund() +
                        ", " + office.getDefaultBonus() + ", " + office.getDefaultManagerBonus()));

        StringBuilder departmentsAppendString = new StringBuilder();
        StringBuilder employeeAppendString = new StringBuilder();
        StringBuilder officeEmployeeAppendString = new StringBuilder();
        StringBuilder departmentEmployeeAppendString = new StringBuilder();
        StringBuilder managerEmployeeAppendString = new StringBuilder();

        for (Department department : office.getDepartmentSet()) {

            departmentsAppendString.append("(").append(department.getOfficeId()).append(", ")
                    .append(department.getSalaryFund()).append(", ")
                    .append(department.getDefaultBonus()).append(", ")
                    .append(department.getDefaultManagerBonus()).append(", ")
                    .append("'").append(department.getDepartmentName()).append("'").append("),");

            for (Employee employee : department.getEmployeeSet()) {

                employeeAppendString.append("(").append(employee.getId()).append(", ")
                        .append(employee.getOfficeId()).append(", ")
                        .append(employee.getDepartmentId()).append(", ")
                        .append("'").append(employee.getFirstName()).append("'").append(", ")
                        .append("'").append(employee.getMiddleName()).append("'").append(", ")
                        .append("'").append(employee.getSecondName()).append("'").append(", ")
                        .append(employee.getBirthDate()).append(", ")
                        .append(employee.getHiringDate()).append(", ")
                        .append(employee.getSalary()).append(", ")
                        .append(employee.getSalaryBonus()).append(", ")
                        .append("'").append(employee.getType().name()).append("'").append("),");

                officeEmployeeAppendString.append("(")
                        .append(employee.getOfficeId()).append(", ")
                        .append(employee.getId()).append(", ").append("),");

                departmentEmployeeAppendString.append("(")
                        .append(employee.getDepartmentId()).append(", ")
                        .append(employee.getId()).append(", ").append("),");

                if (employee instanceof Manager) {
                    for (Employee emp : ((Manager) employee).getWorkers()) {
                        managerEmployeeAppendString.append("(")
                                .append(employee.getId()).append(", ")
                                .append(emp.getId()).append(", ").append("),");
                    }
                }
            }
        }
        if (departmentsAppendString.length() > 0) {
            update(dbc, String.format(query_template, "department",
                    "id, salary_fund, default_bonus, default_manager_bonus, department_name",
                    departmentsAppendString.substring(1, departmentsAppendString.length()-2)));
        }
        if (employeeAppendString.length() > 0) {
            update(dbc, String.format(query_template, "employee",
                    "id, office_id, department_id, first_name, middle_name, second_name, birthday," +
                            " hiring_date, salary, salary_bonus, type",
                    employeeAppendString.substring(1, employeeAppendString.length()-2)));
        }
        if (officeEmployeeAppendString.length() > 0) {
            update(dbc, String.format(query_template, "office_employees",
                    "office_id, employee_id",
                    officeEmployeeAppendString.substring(1, officeEmployeeAppendString.length()-2)));
        }
        if (departmentEmployeeAppendString.length() > 0) {
            update(dbc, String.format(query_template, "department_employees",
                    "department_id, employee_id",
                    departmentEmployeeAppendString.substring(1, departmentEmployeeAppendString.length()-2)));
        }
        if (managerEmployeeAppendString.length() > 0) {
            update(dbc, String.format(query_template, "manager_employees",
                    "manager_id, employee_id",
                    managerEmployeeAppendString.substring(1, managerEmployeeAppendString.length()-2)));
        }

        dbc.closeConnection();
    }
    /*
    public static Office loadOrganizationFromDB(DatabaseConnection dbc, int officeId) {

    }*/
}
