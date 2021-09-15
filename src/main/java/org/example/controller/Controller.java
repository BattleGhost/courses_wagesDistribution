package org.example.controller;

import org.example.model.Model;
import org.example.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
    }
}
