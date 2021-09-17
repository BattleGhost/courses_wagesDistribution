package org.example.controller;

import org.example.model.Model;
import org.example.model.organization.Department;
import org.example.model.organization.Office;
import org.example.model.organization.RecalculationScheme;
import org.example.model.organization.employees.Employee;
import org.example.model.organization.employees.EmployeeType;
import org.example.view.UnpackedConstants;
import org.example.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrganizationController {
    private Model model;
    private View view;
    private Department currentDepartment;
    private Employee currentEmployee;

    public OrganizationController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processOfficeCreation(Scanner scanner) {
        long salaryFund = Long.parseLong(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_OFFICE_FUND,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
        long defaultBonus = Long.parseLong(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_OFFICE_BONUS_EMPLOYEE,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
        long defaultManagerBonus = Long.parseLong(getUserData(scanner,
                UnpackedConstants.MESSAGE_INPUT_OFFICE_BONUS_MANAGER,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
        model.createOffice(salaryFund, defaultBonus, defaultManagerBonus);
    }

    public boolean processOfficeLoad(Scanner scanner) {
        List<Integer> availableOffices = model.getAllOffices();
        if (availableOffices.isEmpty()) {
            view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST_EMPTY,
                    UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_OFFICES));
            return false;
        } else {
            view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST,
                    UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_OFFICES,
                    UnpackedConstants.MESSAGE_CONSTANT_COLON, UnpackedConstants.MESSAGE_CONSTANT_SPACE,
                    view.stringifyList(availableOffices)));
            int chosenId = Integer.parseInt(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_ID_OFFICE,
                    UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
            while (!availableOffices.contains(chosenId)) {
                view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_OPTION);
                chosenId = Integer.parseInt(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_ID_OFFICE,
                        UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
            }
            model.load(chosenId);
            return true;
        }
    }

    public void processOfficeInformation() {
        view.indentLines(1);
        view.showMessage(model.getOffice().toString());
        view.indentLines(1);
    }

    public boolean processDepartmentsShowcase(Scanner scanner) {
        Set<Department> availableDepartments = model.getOffice().getDepartmentSet();
        Set<Integer> availableDepartmentsIds = new LinkedHashSet<>();
        for (Department department: availableDepartments) {
            availableDepartmentsIds.add(department.getDepartmentId());
        }
        if (availableDepartments.isEmpty()) {
            view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST_EMPTY,
                    UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_DEPARTMENTS));
            return false;
        } else {
            view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST,
                    UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_DEPARTMENTS,
                    UnpackedConstants.MESSAGE_CONSTANT_COLON, UnpackedConstants.MESSAGE_CONSTANT_SPACE,
                    view.stringifyList(Arrays.asList(availableDepartments.toArray()))));
            int chosenId = Integer.parseInt(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_ID_DEPARTMENT,
                    UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
            while (!availableDepartmentsIds.contains(chosenId)) {
                view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_OPTION);
                chosenId = Integer.parseInt(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_ID_DEPARTMENT,
                        UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
            }
            for (Department department: availableDepartments) {
                if (department.getDepartmentId() == chosenId) {
                    currentDepartment = department;
                    break;
                }
            }
            return true;
        }
    }

    private void processRecalculation(Scanner scanner, Office organizationUnit) {
        List<Integer> availableSchemes = Arrays.asList(RecalculationScheme.UNIFORM.ordinal(),
                RecalculationScheme.PROPORTIONAL.ordinal());

        int chosenId;
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST,
                UnpackedConstants.MESSAGE_CONSTANT_SPACE,
                UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_SCHEME_RECALCULATION,
                UnpackedConstants.MESSAGE_CONSTANT_COLON, UnpackedConstants.MESSAGE_CONSTANT_SPACE));
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                availableSchemes.get(0).toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_OPTION_PROPORTIONAL));
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                availableSchemes.get(1).toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_OPTION_UNIFORM));
        do {
            chosenId = Integer.parseInt(getUserData(scanner,
                    UnpackedConstants.MESSAGE_OUTPUT_OPTION_CHOOSE,
                    UnpackedConstants.MESSAGE_INPUT_RECALCULATION_SCHEME,
                    UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATA,
                    UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));

            if (!availableSchemes.contains(chosenId)) {
                view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_OPTION);
            }

        } while (!availableSchemes.contains(chosenId));

        organizationUnit.recalculateSalary(RecalculationScheme.values()[chosenId]);
        view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_SUCCESS);
        view.indentLines(1);
    }

    public void processOfficeRecalculation(Scanner scanner) {
        if (model.getOffice() != null) {
            processRecalculation(scanner, model.getOffice());
        }
    }

    public void processDepartmentRecalculation(Scanner scanner) {
        if (currentDepartment != null) {
            processRecalculation(scanner, currentDepartment);
        }
    }

    public void processDepartmentAddition(Scanner scanner) {
        String departmentName = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_DEPARTMENT_NAME,
                UnpackedConstants.INPUT_LETTERS_REGEXP);
        long salaryFund = Long.parseLong(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_OFFICE_FUND,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
        long defaultBonus = Long.parseLong(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_OFFICE_BONUS_EMPLOYEE,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
        long defaultManagerBonus = Long.parseLong(getUserData(scanner,
                UnpackedConstants.MESSAGE_INPUT_OFFICE_BONUS_MANAGER,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
        model.createDepartmentInOffice(salaryFund, defaultBonus, defaultManagerBonus, departmentName);
    }

    public void processDepartmentsInformation() {
        if (currentDepartment != null) {
            view.indentLines(1);
            view.showMessage(currentDepartment.showInfo());
            view.indentLines(1);
        }
    }

    public boolean processEmployeeShowcase(Scanner scanner) {
        if (currentDepartment != null) {
            Set<Employee> availableEmployees = currentDepartment.getEmployeeSet();
            Set<Integer> availableEmployeesIds = new LinkedHashSet<>();
            for (Employee employee: availableEmployees) {
                availableEmployeesIds.add(employee.getId());
            }
            if (availableEmployees.isEmpty()) {
                view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST_EMPTY,
                        UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_EMPLOYEES));
                return false;
            } else {
                view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST,
                        UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_EMPLOYEES,
                        UnpackedConstants.MESSAGE_CONSTANT_COLON, UnpackedConstants.MESSAGE_CONSTANT_SPACE,
                        view.stringifyList(Arrays.asList(availableEmployees.toArray()))));
                int chosenId = Integer.parseInt(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_ID_EMPLOYEE,
                        UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
                while (!availableEmployeesIds.contains(chosenId)) {
                    view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_OPTION);
                    chosenId = Integer.parseInt(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_ID_EMPLOYEE,
                            UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));
                }
                for (Employee employee: availableEmployees) {
                    if (employee.getId() == chosenId) {
                        currentEmployee = employee;
                        break;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    public void processEmployeeAddition(Scanner scanner) {
        String firstName = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_NAME_FIRST,
                UnpackedConstants.INPUT_LETTERS_REGEXP);
        String middleName = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_NAME_MIDDLE,
                UnpackedConstants.INPUT_LETTERS_REGEXP);
        String secondName = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_NAME_SECOND,
                UnpackedConstants.INPUT_LETTERS_REGEXP);

        SimpleDateFormat sdf;
        Date birthDate = new Date();
        Date hireDate = new Date();
        String stringBirthDate = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_REQUEST,
                UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_DATE_BIRTHDAY, UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATE,
                UnpackedConstants.INPUT_DATE_REGEXP);
        String stringHireDate = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_REQUEST,
                UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_DATE_HIRE, UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATE,
                UnpackedConstants.INPUT_DATE_REGEXP);
        boolean birthDateCreated = false;
        boolean hireDateCreated = false;

        List<String> datePatterns = Arrays.asList("dd/MM/yyyy", "d/M/yyyy", "dd/M/yyyy", "d/MM/yyyy", "d/M/yyyy",
                "dd/M/yy", "d/MM/yy", "dd/MM/yy", "dd.MM.yyyy", "d.M.yyyy", "dd.M.yyyy", "d.MM.yyyy", "d.M.yyyy",
                "dd.M.yy", "d.MM.yy", "dd.MM.yy", "dd-MM-yyyy", "d-M-yyyy", "dd-M-yyyy", "d-MM-yyyy", "d-M-yyyy",
                "dd-M-yy", "d-MM-yy", "dd-MM-yy");

        for (String datePattern : datePatterns) {
            sdf = new SimpleDateFormat(datePattern);
            try {
                birthDate = sdf.parse(stringBirthDate);
            } catch (ParseException e) {
                continue;
            }
            birthDateCreated = true;
            break;
        }
        for (String datePattern : datePatterns) {
            sdf = new SimpleDateFormat(datePattern);
            try {
                hireDate = sdf.parse(stringHireDate);
            } catch (ParseException e) {
                continue;
            }
            hireDateCreated = true;
            break;
        }

       if (!birthDateCreated || !hireDateCreated) {
           view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATE);
           return;
       }

        long salary = Long.parseLong(getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_SALARY,
                UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));

        List<Integer> availablePositions = Arrays.asList(EmployeeType.WORKER.ordinal(), EmployeeType.MANAGER.ordinal(),
                EmployeeType.OTHER.ordinal());

        int chosenId;
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_OUTPUT_KEYWORDS_LIST,
                UnpackedConstants.MESSAGE_CONSTANT_COLON, UnpackedConstants.MESSAGE_CONSTANT_SPACE));
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                availablePositions.get(0).toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_OPTION_EMPLOYEE_WORKER));
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                availablePositions.get(1).toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_OPTION_EMPLOYEE_MANAGER));
        view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                availablePositions.get(2).toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                UnpackedConstants.MESSAGE_CONSTANT_SPACE, UnpackedConstants.MESSAGE_OUTPUT_OPTION_EMPLOYEE_OTHER));
        do {
            chosenId = Integer.parseInt(getUserData(scanner,
                    UnpackedConstants.MESSAGE_OUTPUT_OPTION_CHOOSE,
                    UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_TYPE,
                    UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATA,
                    UnpackedConstants.INPUT_INTEGERS_POSITIVE_REGEXP));

            if (!availablePositions.contains(chosenId)) {
                view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_OPTION);
            }

        } while (!availablePositions.contains(chosenId));

        EmployeeType employeeType = EmployeeType.values()[chosenId];

        if (currentDepartment != null) {
            switch (employeeType) {
                case WORKER:
                    model.addWorkerToDepartment(firstName, middleName, secondName, birthDate, hireDate, salary,
                            0, currentDepartment.getDepartmentId());
                    break;
                case MANAGER:
                    model.addManagerToDepartment(firstName, middleName, secondName, birthDate, hireDate, salary,
                            0, currentDepartment.getDepartmentId());
                    break;
                case OTHER:
                    String description = getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_EMPLOYEE_NAME_FIRST,
                            UnpackedConstants.INPUT_LETTERS_REGEXP);
                    model.addOtherToDepartment(firstName, middleName, secondName, birthDate, hireDate, salary,
                            0, currentDepartment.getDepartmentId(), description);
                    break;
            }
            view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_SUCCESS);
            view.indentLines(1);
        }
    }

    public void processEmployeeDeletion(Scanner scanner) {
        if (processEmployeeShowcase(scanner) && currentEmployee != null) {
            model.removeEmployeeFromDepartment(currentDepartment.getDepartmentId(), currentEmployee.getId());
        }
    }

    public void processEmployeeInformation() {
        if (currentEmployee != null) {
            view.indentLines(1);
            view.showMessage(currentEmployee.showInfo());
            view.indentLines(1);
        }
    }

    public void processManagerAttach() {

    }

    public void processManagerDetach() {
    }

    public void processEmployeeTypeChange(Scanner scanner) {
    }

    public void processOfficeSave() {
        model.save();
        view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_SUCCESS);
        view.indentLines(1);
    }

    private String getUserData(Scanner scanner, String requestMessage, String message, String errorMessage,
                               String regex) {
        view.showMessage(view.concatStrings(requestMessage, UnpackedConstants.MESSAGE_CONSTANT_SPACE,
                message, UnpackedConstants.MESSAGE_CONSTANT_COLON));
        String userInput = scanner.next();
        while (!userInput.matches(regex)) {
            view.showMessage(errorMessage);
            userInput = scanner.next();
        }
        return userInput;
    }

    private String getUserData(Scanner scanner, String message, String regex) {
        return getUserData(scanner, UnpackedConstants.MESSAGE_INPUT_REQUEST, message,
                UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATA, regex);
    }
}
