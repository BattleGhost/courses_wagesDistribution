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
    private Stage currentStage;

    private enum Stage {
        START, OFFICE, DEPARTMENT, EMPLOYEE
    }

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.currentStage = Stage.START;
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
                        OrganizationController.processOfficeCreation();
                        currentStage = Stage.OFFICE;
                        break;
                    case 2:
                        OrganizationController.processOfficeLoad();
                        currentStage = Stage.OFFICE;
                        break;
                }
                break;
            case OFFICE:
                switch (chosenOption) {
                    case 0:
                        currentStage = Stage.START;
                    case 1:
                        OrganizationController.processOfficeInformation();
                        break;
                    case 2:
                        OrganizationController.processDepartmentsShowcase();
                        currentStage = Stage.DEPARTMENT;
                        break;
                    case 3:
                        OrganizationController.processRecalculation();
                        break;
                    case 4:
                        OrganizationController.processDepartmentAddition();
                        break;
                }
                break;
            case DEPARTMENT:
                switch (chosenOption) {
                    case 0:
                        currentStage = Stage.OFFICE;
                    case 1:
                        OrganizationController.processDepartmentsInformation();
                        break;
                    case 2:
                        OrganizationController.processEmployeeShowcase();
                        currentStage = Stage.EMPLOYEE;
                        break;
                    case 3:
                        OrganizationController.processRecalculation();
                        break;
                    case 4:
                        OrganizationController.processEmployeeAddition();
                        break;
                    case 5:
                        OrganizationController.processEmployeeDeletion();
                        break;
                }
                break;
            case EMPLOYEE:
                switch (chosenOption) {
                    case 0:
                        currentStage = Stage.DEPARTMENT;
                    case 1:
                        OrganizationController.processEmployeeInformation();
                        break;
                    case 2:
                        OrganizationController.processManagerAttach();
                        break;
                    case 3:
                        OrganizationController.processManagerDetach();
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
                options.put(1, UnpackedConstants.MESSAGE_OUTPUT_OPTION_EMPLOYEE_CHANGE_TYPE);
                options.put(2, UnpackedConstants.MESSAGE_OUTPUT_OPTION_MANAGER_ATTACH);
                options.put(3, UnpackedConstants.MESSAGE_OUTPUT_OPTION_MANAGER_DETACH);
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
