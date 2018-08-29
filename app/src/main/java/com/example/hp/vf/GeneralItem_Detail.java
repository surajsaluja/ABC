package com.example.hp.vf;

/**
 * Created by suraj on 23-08-2018.
 */
public class GeneralItem_Detail extends ListItem {
    private Detail pojoOfJsonArray;

    public Detail getPojoOfJsonArray() {
        return pojoOfJsonArray;
    }

    public void setPojoOfJsonArray(Detail pojoOfJsonArray) {
        this.pojoOfJsonArray = pojoOfJsonArray;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }


}

