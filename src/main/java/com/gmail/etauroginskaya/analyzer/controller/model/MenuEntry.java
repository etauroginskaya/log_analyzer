package com.gmail.etauroginskaya.analyzer.controller.model;

public abstract class MenuEntry {
    private String title;

    public MenuEntry(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract void run();
}