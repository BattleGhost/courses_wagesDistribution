package org.example.controller;

import org.example.model.Model;
import org.example.view.UnpackedConstants;
import org.example.view.View;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;
    private OrganizationController organizationController;
    private Stage currentStage;

    private enum Stage {
        START, OFFICE, DEPARTMENT, EMPLOYEE
    }

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.currentStage = Stage.START;
        this.organizationController = new OrganizationController(model, view);
    }

    public void processUser() {
        Scanner scanner = new Scanner(System.in);
        chooseOption(scanner);
    }

    private void chooseOption(Scanner scanner) {
        int chosenOption = showOptions(scanner);
        switch (currentStage) {
            case START:
                switch (chosenOption) {
                    case 0:
                        return;
                    case 1:
                        organizationController.processOfficeCreation(scanner);
                        currentStage = Stage.OFFICE;
                        break;
                    case 2:
                        if (organizationController.processOfficeLoad(scanner)) {
                            currentStage = Stage.OFFICE;
                        }
                        break;
                }
                break;
            case OFFICE:
                switch (chosenOption) {
                    case 0:
                        currentStage = Stage.START;
                        break;
                    case 1:
                        organizationController.processOfficeInformation();
                        break;
                    case 2:
                        if (organizationController.processDepartmentsShowcase(scanner)) {
                            currentStage = Stage.DEPARTMENT;
                        }
                        break;
                    case 3:
                        organizationController.processOfficeRecalculation(scanner);
                        break;
                    case 4:
                        organizationController.processDepartmentAddition(scanner);
                        break;
                    case 5:
                        organizationController.processOfficeSave();
                        break;
                }
                break;
            case DEPARTMENT:
                switch (chosenOption) {
                    case 0:
                        currentStage = Stage.OFFICE;
                        break;
                    case 1:
                        organizationController.processDepartmentsInformation();
                        break;
                    case 2:
                        if (organizationController.processEmployeeShowcase(scanner)) {
                            currentStage = Stage.EMPLOYEE;
                        }
                        break;
                    case 3:
                        organizationController.processDepartmentRecalculation(scanner);
                        break;
                    case 4:
                        organizationController.processEmployeeAddition(scanner);
                        break;
                    case 5:
                        organizationController.processEmployeeDeletion(scanner);
                        break;
                }
                break;
            case EMPLOYEE:
                switch (chosenOption) {
                    case 0:
                        currentStage = Stage.DEPARTMENT;
                        break;
                    case 1:
                        organizationController.processEmployeeInformation();
                        break;
                    case 2:
                        organizationController.processEmployeeTypeChange(scanner);
                        break;
                    case 3:
                        organizationController.processManagerAttach();
                        break;
                    case 4:
                        organizationController.processManagerDetach();
                        break;
                }
                break;
        }
        chooseOption(scanner);
    }

    private int showOptions(Scanner scanner) {
        Map<Integer, String> options = new LinkedHashMap<>();
        switch (currentStage) {
            case START:
                options.put(1, UnpackedConstants.MESSAGE_OUTPUT_OPTION_CREATE);
                options.put(2, UnpackedConstants.MESSAGE_OUTPUT_OPTION_LOAD);
                options.put(0, UnpackedConstants.MESSAGE_OUTPUT_OPTION_EXIT);
                break;
            case OFFICE:
                options.put(1, UnpackedConstants.MESSAGE_OUTPUT_OPTION_INFORMATION);
                options.put(2, UnpackedConstants.MESSAGE_OUTPUT_OPTION_SHOW_DEPARTMENTS);
                options.put(3, UnpackedConstants.MESSAGE_OUTPUT_OPTION_RECALCULATION);
                options.put(4, UnpackedConstants.MESSAGE_OUTPUT_OPTION_ADD_DEPARTMENT);
                options.put(5, UnpackedConstants.MESSAGE_OUTPUT_OPTION_SAVE);
                options.put(0, UnpackedConstants.MESSAGE_OUTPUT_OPTION_BACK);
                break;
            case DEPARTMENT:
                options.put(1, UnpackedConstants.MESSAGE_OUTPUT_OPTION_INFORMATION);
                options.put(2, UnpackedConstants.MESSAGE_OUTPUT_OPTION_SHOW_EMPLOYEES);
                options.put(3, UnpackedConstants.MESSAGE_OUTPUT_OPTION_RECALCULATION);
                options.put(4, UnpackedConstants.MESSAGE_OUTPUT_OPTION_ADD_EMPLOYEE);
                options.put(5, UnpackedConstants.MESSAGE_OUTPUT_OPTION_DELETE_EMPLOYEE);
                options.put(0, UnpackedConstants.MESSAGE_OUTPUT_OPTION_BACK);
                break;
            case EMPLOYEE:
                options.put(1, UnpackedConstants.MESSAGE_OUTPUT_OPTION_INFORMATION);
                options.put(2, UnpackedConstants.MESSAGE_OUTPUT_OPTION_EMPLOYEE_CHANGE_TYPE);
                options.put(3, UnpackedConstants.MESSAGE_OUTPUT_OPTION_MANAGER_ATTACH);
                options.put(4, UnpackedConstants.MESSAGE_OUTPUT_OPTION_MANAGER_DETACH);
                options.put(0, UnpackedConstants.MESSAGE_OUTPUT_OPTION_BACK);
                break;
        }
        printAvailableOptions(options);
        int chosenOption = getUserInput(scanner, options);
        while (!options.containsKey(chosenOption)) {
            view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_OPTION);
            printAvailableOptions(options);
            chosenOption = getUserInput(scanner, options);
        }
        return chosenOption;
    }

    private int getUserInput(Scanner scanner, Map<Integer, String> options) {
        while (!scanner.hasNextInt()) {
            view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_WRONG_DATA);
            printAvailableOptions(options);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void printAvailableOptions(Map<Integer, String> options) {
        view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_OPTION_CHOOSE);
        for (Map.Entry<Integer, String> entry : options.entrySet()) {
            view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                    entry.getKey().toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                    UnpackedConstants.MESSAGE_CONSTANT_SPACE, entry.getValue()));
        }
    }

}
