package org.example.view;

public class UnpackedConstants {
    public static String MESSAGE_INPUT_REQUEST;
    public static String MESSAGE_INPUT_EMPLOYEE_NAME_SECOND;
    public static String MESSAGE_INPUT_EMPLOYEE_NAME_FIRST;
    public static String MESSAGE_INPUT_EMPLOYEE_NAME_MIDDLE;
    public static String MESSAGE_INPUT_EMPLOYEE_DATE_BIRTHDAY;
    public static String MESSAGE_INPUT_EMPLOYEE_DATE_HIRE;
    public static String MESSAGE_INPUT_EMPLOYEE_SALARY;
    public static String MESSAGE_INPUT_EMPLOYEE_TYPE;
    public static String MESSAGE_INPUT_OFFICE_FUND;
    public static String MESSAGE_INPUT_OFFICE_BONUS_EMPLOYEE;
    public static String MESSAGE_INPUT_OFFICE_BONUS_MANAGER;
    public static String MESSAGE_INPUT_DEPARTMENT_NAME;
    public static String MESSAGE_INPUT_ID_OFFICE;
    public static String MESSAGE_INPUT_ID_DEPARTMENT;
    public static String MESSAGE_INPUT_ID_MANAGER;
    public static String MESSAGE_INPUT_ID_EMPLOYEE;

    public static String MESSAGE_OUTPUT_OPTION_CHOOSE;
    public static String MESSAGE_OUTPUT_OPTION_LOAD;
    public static String MESSAGE_OUTPUT_OPTION_SAVE;
    public static String MESSAGE_OUTPUT_OPTION_INFORMATION;
    public static String MESSAGE_OUTPUT_OPTION_BACK;
    public static String MESSAGE_OUTPUT_OPTION_RECALCULATION;
    public static String MESSAGE_OUTPUT_OPTION_UNIFORM;
    public static String MESSAGE_OUTPUT_OPTION_PROPORTIONAL;
    public static String MESSAGE_OUTPUT_OPTION_SORT;
    public static String MESSAGE_OUTPUT_OPTION_SORT_SURNAME;
    public static String MESSAGE_OUTPUT_OPTION_SORT_HIRE;
    public static String MESSAGE_OUTPUT_OPTION_ADD_DEPARTMENT;
    public static String MESSAGE_OUTPUT_OPTION_ADD_EMPLOYEE;
    public static String MESSAGE_OUTPUT_OPTION_DELETE_EMPLOYEE;
    public static String MESSAGE_OUTPUT_OPTION_MANAGER_ATTACH;
    public static String MESSAGE_OUTPUT_OPTION_MANAGER_DETACH;
    public static String MESSAGE_OUTPUT_OPTION_EMPLOYEE_CHANGE_TYPE;

    public static String MESSAGE_OUTPUT_ERROR_DB;
    public static String MESSAGE_OUTPUT_ERROR_MANAGER;
    public static String MESSAGE_OUTPUT_WRONG_DATE;
    public static String MESSAGE_OUTPUT_WRONG_OPTION;
    public static String MESSAGE_OUTPUT_SUCCESS;

    public static String MESSAGE_CONSTANT_SPACE;
    public static String MESSAGE_CONSTANT_COLON;
    public static String MESSAGE_CONSTANT_DOT;
    public static String MESSAGE_CONSTANT_COMMA;
    public static String MESSAGE_CONSTANT_PARENTHESIS_OPEN;
    public static String MESSAGE_CONSTANT_PARENTHESIS_CLOSE;
    public static String MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN;
    public static String MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE;

    public static String INPUT_LETTERS_REGEXP;
    public static String INPUT_INTEGERS_POSITIVE_REGEXP;
    public static String INPUT_DATE_REGEXP;

    static {
        new View();
    }

    public static void createConstants(View view) {
        MESSAGE_INPUT_REQUEST = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_REQUEST);
        MESSAGE_INPUT_EMPLOYEE_NAME_SECOND = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_NAME_SECOND);
        MESSAGE_INPUT_EMPLOYEE_NAME_FIRST = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_NAME_FIRST);
        MESSAGE_INPUT_EMPLOYEE_NAME_MIDDLE = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_NAME_MIDDLE);
        MESSAGE_INPUT_EMPLOYEE_DATE_BIRTHDAY =
                view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_DATE_BIRTHDAY);
        MESSAGE_INPUT_EMPLOYEE_DATE_HIRE = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_DATE_HIRE);
        MESSAGE_INPUT_EMPLOYEE_SALARY = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_SALARY);
        MESSAGE_INPUT_EMPLOYEE_TYPE = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMPLOYEE_TYPE);
        MESSAGE_INPUT_OFFICE_FUND = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_OFFICE_FUND);
        MESSAGE_INPUT_OFFICE_BONUS_EMPLOYEE =
                view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_OFFICE_BONUS_EMPLOYEE);
        MESSAGE_INPUT_OFFICE_BONUS_MANAGER = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_OFFICE_BONUS_MANAGER);
        MESSAGE_INPUT_DEPARTMENT_NAME = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_DEPARTMENT_NAME);
        MESSAGE_INPUT_ID_OFFICE = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ID_OFFICE);
        MESSAGE_INPUT_ID_DEPARTMENT = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ID_DEPARTMENT);
        MESSAGE_INPUT_ID_MANAGER = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ID_MANAGER);
        MESSAGE_INPUT_ID_EMPLOYEE = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ID_EMPLOYEE);
        MESSAGE_OUTPUT_OPTION_CHOOSE = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_CHOOSE);
        MESSAGE_OUTPUT_OPTION_LOAD = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_LOAD);
        MESSAGE_OUTPUT_OPTION_SAVE = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_SAVE);
        MESSAGE_OUTPUT_OPTION_INFORMATION = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_INFORMATION);
        MESSAGE_OUTPUT_OPTION_BACK = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_BACK);
        MESSAGE_OUTPUT_OPTION_RECALCULATION =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_RECALCULATION);
        MESSAGE_OUTPUT_OPTION_UNIFORM = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_UNIFORM);
        MESSAGE_OUTPUT_OPTION_PROPORTIONAL =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_PROPORTIONAL);
        MESSAGE_OUTPUT_OPTION_SORT = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_SORT);
        MESSAGE_OUTPUT_OPTION_SORT_SURNAME = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_SORT_SURNAME);
        MESSAGE_OUTPUT_OPTION_SORT_HIRE = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_SORT_HIRE);
        MESSAGE_OUTPUT_OPTION_ADD_DEPARTMENT =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_ADD_DEPARTMENT);
        MESSAGE_OUTPUT_OPTION_ADD_EMPLOYEE = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_ADD_EMPLOYEE);
        MESSAGE_OUTPUT_OPTION_DELETE_EMPLOYEE =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_DELETE_EMPLOYEE);
        MESSAGE_OUTPUT_OPTION_MANAGER_ATTACH =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_MANAGER_ATTACH);
        MESSAGE_OUTPUT_OPTION_MANAGER_DETACH =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_MANAGER_DETACH);
        MESSAGE_OUTPUT_OPTION_EMPLOYEE_CHANGE_TYPE =
                view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_OPTION_EMPLOYEE_CHANGE_TYPE);
        MESSAGE_OUTPUT_ERROR_DB = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_ERROR_DB);
        MESSAGE_OUTPUT_ERROR_MANAGER = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_ERROR_MANAGER);
        MESSAGE_OUTPUT_WRONG_DATE = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_WRONG_DATE);
        MESSAGE_OUTPUT_WRONG_OPTION = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_WRONG_OPTION);
        MESSAGE_OUTPUT_SUCCESS = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_SUCCESS);
        MESSAGE_CONSTANT_SPACE = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_SPACE);
        MESSAGE_CONSTANT_COLON = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_COLON);
        MESSAGE_CONSTANT_DOT = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_DOT);
        MESSAGE_CONSTANT_COMMA = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_COMMA);
        MESSAGE_CONSTANT_PARENTHESIS_OPEN = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_PARENTHESIS_OPEN);
        MESSAGE_CONSTANT_PARENTHESIS_CLOSE =
                view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_PARENTHESIS_CLOSE);
        MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN =
                view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_OPEN);
        MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE =
                view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_SQUARE_BRACKET_CLOSE);
        INPUT_LETTERS_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_LETTERS_REGEXP);
        INPUT_INTEGERS_POSITIVE_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_INTEGERS_POSITIVE_REGEXP);
        INPUT_DATE_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_DATE_REGEXP);

    }

}
