package com.schoters.mail.schoters;

import android.widget.CheckBox;

import java.util.ArrayList;

public class ListCheckBox {
    public ArrayList<CheckBox> array = new ArrayList<>();

    public ListCheckBox() { }

    public ListCheckBox(CheckBox checkBox) {
        array.add(checkBox);
    }

    public ArrayList<CheckBox> getArray() {
        return array;
    }
}
