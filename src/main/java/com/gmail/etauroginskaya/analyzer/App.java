package com.gmail.etauroginskaya.analyzer;

import com.gmail.etauroginskaya.analyzer.controller.Menu;
import com.gmail.etauroginskaya.analyzer.controller.validation.impl.ValidationServiceImpl;

import java.util.MissingResourceException;

public class App {
    public static void main(String[] args) {
        System.out.println("Hi " + System.getProperty("user.name") + "!");
        try {
            if (!ValidationServiceImpl.getInstance().validateRequiredProperty()) {
                System.exit(0);
            }
            Menu menu = new Menu();
            menu.run();
        } catch (MissingResourceException e) {
            System.out.println(String
                    .format("Sorry! There is no required application parameter %s in the configuration file.", e.getKey()));
        }
    }
}