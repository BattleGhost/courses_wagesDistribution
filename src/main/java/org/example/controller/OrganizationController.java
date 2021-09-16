package org.example.controller;

import org.example.model.Model;
import org.example.model.organization.Department;
import org.example.model.organization.Office;
import org.example.model.organization.RecalculationScheme;
import org.example.model.organization.employees.Employee;
import org.example.view.UnpackedConstants;
import org.example.view.View;

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

    public void processDepartmentAddition() {
    }

    public void processDepartmentsInformation() {
    }

    public void processEmployeeShowcase() {
    }

    public void processEmployeeAddition() {
    }

    public void processEmployeeDeletion() {
    }

    public void processEmployeeInformation() {
    }

    public void processManagerAttach() {
    }

    public void processManagerDetach() {
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
