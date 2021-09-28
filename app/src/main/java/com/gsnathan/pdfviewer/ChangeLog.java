package com.gsnathan.pdfviewer;

public class ChangeLog {

    private String title;
    private String description;
    private int iconRes;

    public ChangeLog() {
    }

    public ChangeLog(String title, String description, int iconRes) {
        this.title = title;
        this.description = description;
        this.iconRes = iconRes;
    }

    public String getTitle() {
        return title;
    }

    public ChangeLog setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ChangeLog setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getIconRes() {
        return iconRes;
    }

    public ChangeLog setIconRes(int iconRes) {
        this.iconRes = iconRes;
        return this;
    }
}
