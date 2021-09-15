package org.example.controller;

import org.example.model.Model;
import org.example.view.UnpackedConstants;
import org.example.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

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
        showOptions();
    }

    private void showOptions() {
        view.showMessage(UnpackedConstants.MESSAGE_OUTPUT_OPTION_CHOOSE);
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

        for (Map.Entry<Integer, String> entry : options.entrySet()) {
            view.showMessage(view.concatStrings(UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN,
                    entry.getKey().toString(), UnpackedConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE,
                    UnpackedConstants.MESSAGE_CONSTANT_SPACE, entry.getValue()));
        }
    }

}
