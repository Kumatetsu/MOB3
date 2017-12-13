package com.etna.mob3.mob3.classes;

/**
 * Created by jeremydebelleix on 06/12/2017.
 */

public class DataModel {

    public String name;
    public String path;
    public boolean checked;
    public boolean isCheckVisible;

    public DataModel(String name, String path, boolean checked, boolean checkVisibility) {
        this.name = name;
        this.path = path;
        this.checked = checked;
        this.isCheckVisible = checkVisibility;

    }
}