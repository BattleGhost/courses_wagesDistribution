package org.example.view;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    private Locale locale;
    private ResourceBundle resourceBundle;

    public View(Locale locale) {
        this.locale = locale;
        this.resourceBundle = ResourceBundle.getBundle(GlobalConstants.BUNDLE_NAME, this.locale);
        UnpackedConstants.createConstants(this);
    }

    public View() {
        this(new Locale(GlobalConstants.DEFAULT_LOCALE_LANGUAGE, GlobalConstants.DEFAULT_LOCALE_COUNTRY));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getTextFromBundle(String key) {
        return this.resourceBundle.getString(key);
    }

    public String concatStrings(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

    public String stringifyList(List<?> list) {
        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            sb.append(item).append(UnpackedConstants.MESSAGE_CONSTANT_COMMA).
                    append(UnpackedConstants.MESSAGE_CONSTANT_SPACE);
        }
        return sb.substring(0,sb.length()-2);
    }

}
