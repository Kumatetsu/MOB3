package com.etna.mob3.mob3.classes;

/**
 * Created by jeremydebelleix on 06/12/2017.
 */

public class DataModel {

    public String name;
    boolean checked;
    boolean isCheckVisible;

    public DataModel(String name, boolean checked, boolean checkVisibility) {
        this.name = name;
        this.checked = checked;
        this.isCheckVisible = checkVisibility;

    }
}