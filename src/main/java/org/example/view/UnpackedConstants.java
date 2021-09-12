package org.example.view;

public class UnpackedConstants {
    public static String MESSAGE_INPUT_REQUEST;
    public static String MESSAGE_INPUT_SURNAME;
    public static String MESSAGE_INPUT_NAME;
    public static String MESSAGE_INPUT_NICKNAME;
    public static String MESSAGE_INPUT_COMMENT;
    public static String MESSAGE_INPUT_GROUP;
    public static String MESSAGE_INPUT_PHONE_HOME;
    public static String MESSAGE_INPUT_PHONE_CELLULAR_FIRST;
    public static String MESSAGE_INPUT_PHONE_CELLULAR_SECOND;
    public static String MESSAGE_INPUT_EMAIL;
    public static String MESSAGE_INPUT_SKYPE;
    public static String MESSAGE_INPUT_ADDRESS_ZIP;
    public static String MESSAGE_INPUT_ADDRESS_CITY;
    public static String MESSAGE_INPUT_ADDRESS_STREET;
    public static String MESSAGE_INPUT_ADDRESS_BUILDING;
    public static String MESSAGE_INPUT_ADDRESS_APARTMENT;

    public static String MESSAGE_OUTPUT_WRONG_DATA;
    public static String MESSAGE_OUTPUT_WRONG_GROUP;
    public static String MESSAGE_OUTPUT_WRONG_LOGIN_TAKEN;
    public static String MESSAGE_OUTPUT_AVAILABLE_GROUPS;
    public static String MESSAGE_OUTPUT_SUCCESS;

    public static String MESSAGE_CONSTANT_SPACE;
    public static String MESSAGE_CONSTANT_COLON ;
    public static String MESSAGE_CONSTANT_DOT ;
    public static String MESSAGE_CONSTANT_COMMA ;
    public static String MESSAGE_CONSTANT_PARENTHESIS_OPEN ;
    public static String MESSAGE_CONSTANT_PARENTHESIS_CLOSE ;
    public static String MESSAGE_CONSTANT_GROUPS_FAMILY;
    public static String MESSAGE_CONSTANT_GROUPS_FRIENDS;
    public static String MESSAGE_CONSTANT_GROUPS_COWORKERS;
    public static String MESSAGE_CONSTANT_GROUPS_OTHER;

    public static String INPUT_NAME_REGEXP;
    public static String INPUT_SURNAME_REGEXP;
    public static String INPUT_NICKNAME_REGEXP;
    public static String INPUT_GROUP_REGEXP;
    public static String INPUT_COMMENT_REGEXP;
    public static String INPUT_PHONE_HOME_REGEXP;
    public static String INPUT_PHONE_CELLULAR_FIRST_REGEXP;
    public static String INPUT_PHONE_CELLULAR_SECOND_REGEXP;
    public static String INPUT_EMAIL_REGEXP;
    public static String INPUT_SKYPE_REGEXP;
    public static String INPUT_ADDRESS_ZIP_REGEXP;
    public static String INPUT_ADDRESS_CITY_REGEXP;
    public static String INPUT_ADDRESS_STREET_REGEXP;
    public static String INPUT_ADDRESS_BUILDING_REGEXP;
    public static String INPUT_ADDRESS_APARTMENT_REGEXP;

    static {
        new View();
    }

    public static void createConstants(View view) {

        MESSAGE_INPUT_REQUEST = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_REQUEST);
        MESSAGE_INPUT_SURNAME = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_SURNAME);
        MESSAGE_INPUT_NAME = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_NAME);
        MESSAGE_INPUT_NICKNAME = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_NICKNAME);
        MESSAGE_INPUT_COMMENT = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_COMMENT);
        MESSAGE_INPUT_GROUP = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_GROUP);
        MESSAGE_INPUT_PHONE_HOME = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_PHONE_HOME);
        MESSAGE_INPUT_PHONE_CELLULAR_FIRST = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_PHONE_CELLULAR_FIRST);
        MESSAGE_INPUT_PHONE_CELLULAR_SECOND = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_PHONE_CELLULAR_SECOND);
        MESSAGE_INPUT_EMAIL = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_EMAIL);
        MESSAGE_INPUT_SKYPE = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_SKYPE);
        MESSAGE_INPUT_ADDRESS_ZIP = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ADDRESS_ZIP);
        MESSAGE_INPUT_ADDRESS_CITY = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ADDRESS_CITY);
        MESSAGE_INPUT_ADDRESS_STREET = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ADDRESS_STREET);
        MESSAGE_INPUT_ADDRESS_BUILDING = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ADDRESS_BUILDING);
        MESSAGE_INPUT_ADDRESS_APARTMENT = view.getTextFromBundle(GlobalConstants.MESSAGE_INPUT_ADDRESS_APARTMENT);
        MESSAGE_OUTPUT_WRONG_DATA = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_WRONG_DATA);
        MESSAGE_OUTPUT_WRONG_GROUP = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_WRONG_GROUP);
        MESSAGE_OUTPUT_WRONG_LOGIN_TAKEN = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_WRONG_LOGIN_TAKEN);
        MESSAGE_OUTPUT_AVAILABLE_GROUPS = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_AVAILABLE_GROUPS);
        MESSAGE_OUTPUT_SUCCESS = view.getTextFromBundle(GlobalConstants.MESSAGE_OUTPUT_SUCCESS);
        MESSAGE_CONSTANT_SPACE = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_SPACE);
        MESSAGE_CONSTANT_COLON  = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_COLON );
        MESSAGE_CONSTANT_DOT  = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_DOT );
        MESSAGE_CONSTANT_COMMA  = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_COMMA );
        MESSAGE_CONSTANT_PARENTHESIS_OPEN  = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_PARENTHESIS_OPEN );
        MESSAGE_CONSTANT_PARENTHESIS_CLOSE  = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_PARENTHESIS_CLOSE );
        MESSAGE_CONSTANT_GROUPS_FAMILY = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_GROUPS_FAMILY);
        MESSAGE_CONSTANT_GROUPS_FRIENDS = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_GROUPS_FRIENDS);
        MESSAGE_CONSTANT_GROUPS_COWORKERS = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_GROUPS_COWORKERS);
        MESSAGE_CONSTANT_GROUPS_OTHER = view.getTextFromBundle(GlobalConstants.MESSAGE_CONSTANT_GROUPS_OTHER);
        INPUT_NAME_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_NAME_REGEXP);
        INPUT_SURNAME_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_SURNAME_REGEXP);
        INPUT_NICKNAME_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_NICKNAME_REGEXP);
        INPUT_GROUP_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_GROUP_REGEXP);
        INPUT_COMMENT_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_COMMENT_REGEXP);
        INPUT_PHONE_HOME_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_PHONE_HOME_REGEXP);
        INPUT_PHONE_CELLULAR_FIRST_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_PHONE_CELLULAR_FIRST_REGEXP);
        INPUT_PHONE_CELLULAR_SECOND_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_PHONE_CELLULAR_SECOND_REGEXP);
        INPUT_EMAIL_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_EMAIL_REGEXP);
        INPUT_SKYPE_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_SKYPE_REGEXP);
        INPUT_ADDRESS_ZIP_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_ADDRESS_ZIP_REGEXP);
        INPUT_ADDRESS_CITY_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_ADDRESS_CITY_REGEXP);
        INPUT_ADDRESS_STREET_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_ADDRESS_STREET_REGEXP);
        INPUT_ADDRESS_BUILDING_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_ADDRESS_BUILDING_REGEXP);
        INPUT_ADDRESS_APARTMENT_REGEXP = view.getTextFromBundle(GlobalConstants.INPUT_ADDRESS_APARTMENT_REGEXP);

    }

}
